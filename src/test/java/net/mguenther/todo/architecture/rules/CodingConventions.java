package net.mguenther.todo.architecture.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.io.Serializable;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class CodingConventions {

    @ArchTest
    static final ArchRule jpa_entities_should_implement_serializable =
            classes().that()
                    .areAnnotatedWith(Entity.class)
                    .should().implement(Serializable.class);

    @ArchTest
    static final ArchRule classes_that_extend_from_repository_should_be_named_properly =
            classes().that()
                    .areInterfaces().and().implement(Repository.class)
                    .should().haveSimpleNameEndingWith("Repository");
}
