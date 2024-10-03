package net.oceandepth.journalApp.service;

import net.oceandepth.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("bilbo").password("baggins").build()),
                Arguments.of(User.builder().userName("golum").password("").build())
        );
    }
}
