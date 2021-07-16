package study.study.repository;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.study.StudyApplicationTests;
import study.study.model.entity.Category;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {

        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);
    }

    @Test
    public void read() {

        //Optional<Category> optionalCategory = categoryRepository.findById(1L);

        String type = "COMPUTER";
        //select * from category where type = 'COMPUTER'
        Optional<Category> optionalCategory = categoryRepository.findByType(type);

        optionalCategory.ifPresent( c-> {

            Assert.assertEquals(c.getType(), type);

            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });

    }
}
