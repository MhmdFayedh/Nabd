package sa.mhmd.nabd.modules.cms;

import org.mapstruct.*;
import sa.mhmd.nabd.modules.cms.dto.ContentResponse;
import sa.mhmd.nabd.modules.cms.dto.CreateContentRequest;
import sa.mhmd.nabd.modules.cms.dto.UpdateContentRequest;
import sa.mhmd.nabd.modules.cms.entity.ContentEntity;
import sa.mhmd.nabd.modules.cms.model.ContentModel;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ContentMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    ContentEntity toEntity(CreateContentRequest request);

    ContentModel toModel(ContentEntity entity);

    ContentEntity toJpaEntity(ContentModel model);

    ContentResponse toResponse(ContentEntity entity);

    ContentEntity toUpdateEntity(UpdateContentRequest request);

    ContentResponse toResponse(UpdateContentRequest model);

    void updateEntityFromRequest(UpdateContentRequest request, @MappingTarget ContentEntity entity);
}

