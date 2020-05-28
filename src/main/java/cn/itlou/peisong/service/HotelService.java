package cn.itlou.peisong.service;

import cn.itlou.peisong.dto.HotelESDTO;
import cn.itlou.peisong.dto.HotelImportDTO;
import cn.itlou.peisong.mapper.HotelMapper;
import cn.itlou.peisong.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店信息处理Service
 *
 * @author yuanyl
 * @date 2020/5/18 18:42
 **/
@Service
@Slf4j
public class HotelService {

    @Resource
    HotelMapper hotelMapper;
    @Resource
    RedisUtils redisUtils;
    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    public Integer insertHotelInfo(List<HotelImportDTO> list){
        log.info("{}条数据，开始存储数据库！", list.size());
        return hotelMapper.insertHotelInfo(list);
    }

    public Long insertHotelInfoToRedis(List<HotelImportDTO> list){
        log.info("{}条数据，开始存储Redis！", list.size());
        return redisUtils.setGeo(list);
    }

    public void insertHotelInfoToElasticSearch(){
        List<HotelESDTO> list = hotelMapper.selectAll();
//        SampleEntity a = new SampleEntity();
//        hotelEsRepository.save(list);
        int counter = 0;
        try {
            List<IndexQuery> queries = Lists.newArrayList();
            for (HotelESDTO item : list) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(item.getId());
                indexQuery.setSource(JSON.toJSONString(item));
                indexQuery.setIndexName("hotel_info");
                indexQuery.setType("_doc");
                queries.add(indexQuery);
                //分批提交索引
                if (counter != 0 && counter % 1000 == 0) {
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                    log.info("bulkIndex counter : {}", counter);
                }
                counter++;
            }
            //不足批的索引最后不要忘记提交
            if (!queries.isEmpty()) {
                elasticsearchTemplate.bulkIndex(queries);
            }
            elasticsearchTemplate.refresh("hotel_info");
            log.info("bulkIndex completed.");
        } catch (Exception e) {
            log.error("IndexerService.bulkIndex e : {}", e.getMessage());
            throw e;
        }
    }

    public String getNearHotelInfo(){
        List<String> ids = redisUtils.getDistanceByCoordinate(118.03911129898074, 24.491372834479673, 5);
        List<HotelImportDTO> hotelImportDTOS = hotelMapper.selectByIdList(ids);
        for (HotelImportDTO hotelImportDTO : hotelImportDTOS) {
            System.out.println(hotelImportDTO.getName());
            System.out.println(hotelImportDTO.getAddress().replace("福建省厦门市", ""));
        }
        return "";
    }

    public List<HotelESDTO> searchByKeyword(String keyword){
        Client client =elasticsearchTemplate.getClient();
        MatchQueryBuilder query = QueryBuilders.matchQuery("name", keyword);
        SearchResponse response = client.prepareSearch("hotel_info").setQuery(query).
                setFrom(0).setSize(10).execute().actionGet();
        SearchHits shs = response.getHits();
        for (SearchHit hit : shs) {
            System.out.println("分数:"

                    + hit.getScore()

                    + ",ID:"

                    + hit.getId()

                    + ", 酒店:"

                    + hit.getSourceAsString());
        }
        return null;
    }

}
