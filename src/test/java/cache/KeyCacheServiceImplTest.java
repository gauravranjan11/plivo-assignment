package cache;

import com.plivo.assignment.cache.*;
import com.plivo.assignment.cache.service.impl.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KeyCacheServiceImplTest {

    @InjectMocks
    private static KeyCacheServiceImpl keyCacheServiceImpl = new KeyCacheServiceImpl();

    @Mock
    RedisManager redisManager;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testPutAndGetForCacheService() {

        when(redisManager.get(any())).thenReturn("VALUE");
        keyCacheServiceImpl.put("KEY", "VALUE", 4);

        Object value = redisManager.get("VALUE");
        Assert.assertThat(value, instanceOf(String.class));
        String testObject = (String) value;
        Assert.assertEquals("VALUE", testObject);

    }

    @Test
    public void testReachedLimit() {

        when(redisManager.checkLimit(anyString(),anyInt(),anyInt())).thenReturn(false);

        Assert.assertFalse(keyCacheServiceImpl.getLimit("Reachedlimit",1,1));
    }

    @Test
    public void testAvailableLimit() {

        when(redisManager.checkLimit(anyString(),anyInt(),anyInt())).thenReturn(true);

        Assert.assertTrue(keyCacheServiceImpl.getLimit("Reachedlimit",1,1));
    }

}
