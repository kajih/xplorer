package xyz.kajih.xplorer.db.internal;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import xyz.kajih.xplorer.product.ProductDTO;

@Mapper
public interface ProductEntiyMapper {

    ProductEntiyMapper MAPPER = Mappers.getMapper(ProductEntiyMapper.class);

    @Mapping( target = "id", ignore = true )
    ProductEntity toEntity(ProductDTO s);

    @InheritInverseConfiguration
    ProductDTO toRecord(ProductEntity entity);

    /* lookup id example
    @ObjectFactory
    default <T extends Entity> T lookup(Toothbrush brush, ToothPaste paste, @Context ArticleRepository repo,
                                        @TargetType Class<T> targetType ) {
        ComposedKey key = new ComposedKey(brush.getName(), paste.getName() );
        CombinedOfferingEntity entity = repo.lookup( key );
        if ( entity == null ) {
            entity = new CombinedOfferingEntity();
        }
        return (T) entity;
    }
     */
}