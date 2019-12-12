package net.mguenther.todo.architecture.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class HorizontalLayering {

    @ArchTest
    static final ArchRule repositories_should_only_be_accessed_by_services =
            classes().that()
                    .haveNameMatching(".*Repository")
                    .should().onlyBeAccessed().byClassesThat().haveNameMatching(".*Service");

    @ArchTest
    static final ArchRule services_should_only_be_accessed_by_controllers =
            classes().that()
                    .haveNameMatching(".*Service")
                    .should().onlyBeAccessed().byClassesThat().haveNameMatching(".*Controller|.*Service");
}
