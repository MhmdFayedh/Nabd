package sa.mhmd.nabd.modules.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.mhmd.nabd.modules.cms.entity.ContentEntity;

@Repository
interface ContentRepositoryJpa extends JpaRepository<ContentEntity, Long> {
}
