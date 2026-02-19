package sa.mhmd.nabd.modules.discovery;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import sa.mhmd.nabd.modules.discovery.model.ContentDocument;



@Repository
interface DiscoveryRepository extends ElasticsearchRepository<ContentDocument, String> {

}
