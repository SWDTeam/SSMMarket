package thupnm.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thupnm.project.domain.Category;
import thupnm.project.domain.Product;
import thupnm.project.dto.CategoryDTO;
import thupnm.project.dto.ProductDTO;
import thupnm.project.repository.CategoryRepository;
import thupnm.project.repository.ProductRepository;
import thupnm.project.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    //private ProductMapperService productMapperService;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        //this.productMapperService = productMapperService;
    }

    @Override
    public Page<ProductDTO> getAll(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> productTables = productPage.getContent();
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDTO> productDTOS = new ArrayList<>();
        productTables.stream().forEach(e -> {
            ProductDTO dto = modelMapper.map(e, ProductDTO.class);
            productDTOS.add(dto);
        });

        //List<ProductDTO> productDTOS = productMapperService.mappingEntity(productTables);
        return new PageImpl<>(productDTOS, pageable, productPage.getTotalElements());
    }

    @Override
    public List<CategoryDTO> getCategory() {
        List<Category> categories = categoryRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categories.stream().forEach(e -> {
            CategoryDTO dto = modelMapper.map(e, CategoryDTO.class);
            categoryDTOS.add(dto);
        });
        return categoryDTOS;
    }
}
