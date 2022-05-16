import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.spy;

import static org.mockito.Mockito.doReturn;

class TestDemoTest {
    
    private TestDemo testDemo; 

    @BeforeEach
    void setUp() throws Exception {
        testDemo = new TestDemo();
    }

    @ParameterizedTest
    @MethodSource("TestDemoTest#argumentsForAddPositive")
    void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
        if(!expectException) {
            assertEquals( expected , testDemo.addPositive(a, b));
        }else {
            assertThatThrownBy(() -> 
            testDemo.addPositive(a, b))
                .isInstanceOf(IllegalArgumentException.class);

        }
    }
    
    public static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
                arguments(2, 4, 6, false),
                arguments(1, 3, 4, false),
                arguments(2, -4, 6, true),
                arguments(-2, 4, 6, true),
                arguments(0, 4, 4, true),
                arguments(2, 0, 6, true));
    }
    
    @Test
    void assertThatNumberSquaredIsCorrect(){
        TestDemo mockDemo = spy(testDemo);
        doReturn(5).when(mockDemo).getRandomInt();
        
        int fiveSquared = mockDemo.randomNumberSquared();
        assertEquals(25, fiveSquared);
    }

}
