//package thupnm.project.service.impl;
//
//import org.springframework.stereotype.Service;
//import thupnm.project.domain.Product;
//import thupnm.project.dto.ProductDTO;
//import thupnm.project.mapper.ProductMapper;
//import thupnm.project.service.ProductMapperService;
//
//import java.util.List;
//
//@Service
//public class ProductMapperServiceImpl implements ProductMapperService {
//
//    private ProductMapper productMapper;
//
//    public ProductMapperServiceImpl(ProductMapper productMapper) {
//        this.productMapper = productMapper;
//    }
//
//    @Override
//    public Product mappingDto(ProductDTO productDTO) {
//        return productMapper.toDto(productDTO);
//    }
//
//    @Override
//    public ProductDTO mappingEntity(Product product) {
//        return productMapper.toEntity(product);
//    }
//
//    @Override
//    public List<Product> mappingDto(List<ProductDTO> productDTOS) {
//        return productMapper.toDto(productDTOS);
//    }
//
//    @Override
//    public List<ProductDTO> mappingEntity(List<Product> products) {
//        return productMapper.toEntity(products);
//    }
//}
