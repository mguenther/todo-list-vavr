package net.mguenther.todo.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import net.mguenther.todo.architecture.rules.CodingConventions;
import net.mguenther.todo.architecture.rules.HorizontalLayering;

@AnalyzeClasses(packages = "net.mguenther.todo")
class ArchitectureComplianceTest {

    @ArchTest
    static final ArchRules codingConventions = ArchRules.in(CodingConventions.class);

    @ArchTest
    static final ArchRules horizontalLayering = ArchRules.in(HorizontalLayering.class);
}
