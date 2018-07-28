package com.example.space;

import com.example.space.api.domain.primary.User;
import com.example.space.api.domain.primary.UserMongoRepository;
import com.example.space.api.domain.primary.UserRepository;
import com.example.space.api.domain.secondary.MessageRepository;
import com.example.space.api.service.UserService;
import com.example.space.framework.asyncTask.Task;
import com.example.space.framework.properties.ProjectProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.apache.log4j.Logger;



import java.util.concurrent.Future;
//import  org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * @author liyu
 * @date 18-7-26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    //StringRedisTemplate就相当于RedisTemplate<String, String>的实现
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private UserMongoRepository userMongoRepository;

   /* @Before
    public void setUp() {
        //运行测试前需要改动测试的controller
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }
    */
/*
    @Test
    public void testUserController() throws Exception {
        RequestBuilder requestBuilder = null;

       //1. get
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //2.post
        requestBuilder = post("/users/")
                .param("id", "1")
                .param("name", "testname")
                .param("age", "20");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));


        //3.get users
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"testname\",\"age\":20}]")));

        //4.put
        requestBuilder = put("/users/1")
                .param("name", "changedname")
                .param("age", "30");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

        //5.get id=1
        requestBuilder = get("/users/1");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo({\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));


        //6. delete id=1
        requestBuilder = delete("/users/1");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

    }
    */

    /*@Before
    public void setUp() {
        userService.deleteAllUsers();
    }

    @Test
    public void test() {
        userService.create("a", 1);
        userService.create("b", 2);
        userService.create("c", 3);
        userService.create("d", 4);
        userService.create("e", 5);

        Assert.assertEquals(5, userService.getAllUsers().intValue());

        userService.deleteByName("a");
        userService.deleteByName("e");

        Assert.assertEquals(3, userService.getAllUsers().intValue());
    }*/
/*
    @Test
    public void test(){
        // 创建10条记录
        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
        userRepository.save(new User("CCC", 30));
        userRepository.save(new User("DDD", 40));
        userRepository.save(new User("EEE", 50));
        userRepository.save(new User("FFF", 60));
        userRepository.save(new User("GGG", 70));
        userRepository.save(new User("HHH", 80));
        userRepository.save(new User("III", 90));
        userRepository.save(new User("JJJ", 100));

        // 测试findAll, 查询所有记录
        Assert.assertEquals(10, userRepository.findAll().size());

        // 测试findByName, 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());

        // 测试findUser, 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());

        // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
        Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());

        // 测试删除姓名为AAA的User
        userRepository.delete(userRepository.findByName("AAA"));

        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        Assert.assertEquals(9, userRepository.findAll().size());
    }*/

    /*@Before
    public void setUp(){
        jdbcTemplate1.update("DELETE FROM user");
        jdbcTemplate2.update("delete from user");
    }

    @Test
    public void test(){
        jdbcTemplate1.update("insert into user(id, name, age) values (?,?,?)", 1, "aaa", 20);
        jdbcTemplate1.update("insert into user(id, name, age) values (?,?,?)", 2, "bbb", 30);

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        jdbcTemplate2.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));

    }*/

    /*@Test
    public void test(){
        userRepository.save(new User("aaa", 10));
        userRepository.save(new User("bbb", 20));
        userRepository.save(new User("ccc", 30));
        userRepository.save(new User("ddd", 40));
        userRepository.save(new User("eee", 50));

        Assert.assertEquals(17, userRepository.findAll().size());

        messageRepository.save(new Message("o1", "aaaaaaaaaa"));
        messageRepository.save(new Message("o2", "bbbbbbbbbb"));
        messageRepository.save(new Message("o3", "cccccccccc"));

        Assert.assertEquals(3, messageRepository.findAll().size());
    }*/

    /*@Test
    public void test(){
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }*/

   /* @Test
    public void test() {
        User user = new User("超人", 20);
        redisTemplate.opsForValue().set(user.getName(), user);
        user = new User("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getName(), user);

        user = new User("蜘蛛侠", 40);
        redisTemplate.opsForValue().set(user.getName(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
    }*/

   /*@Before
   public void setUp(){
       userMongoRepository.deleteAll();
   }

   @Test
   public void test() {
       userMongoRepository.save(new User(1L, "didi", 30));
       userMongoRepository.save(new User(2L, "mama", 40));
       userMongoRepository.save(new User(3L, "kaka", 50));
       Assert.assertEquals(3, userMongoRepository.findAll().size());

       // 删除一个User，再验证User总数
       User u = userMongoRepository.findUserById(1L);
       userMongoRepository.delete(u);
       Assert.assertEquals(2, userMongoRepository.findAll().size());

       // 删除一个User，再验证User总数
       u = userMongoRepository.findByName("mama");
       userMongoRepository.delete(u);
       Assert.assertEquals(1, userMongoRepository.findAll().size());
   }*/

   /*@Autowired
    private ProjectProperties projectProperties;

   @Test
   public void getProperties(){
       Assert.assertEquals(projectProperties.getName(), "this is author name");
       Assert.assertEquals(projectProperties.getTitle(), "springboot实战项目");
   }*/

  /* @Autowired
    private Task task;

   @Test
   public void test() throws InterruptedException {
      *//* task.doTaskOne();
       task.doTaskTwo();
       task.doTaskThree();
*//*
       long start = System.currentTimeMillis();
       Future<String> task1 = task.doTaskOne1();
       Future<String> task2 = task.doTaskTwo2();
       Future<String> task3 = task.doTaskThree3();
       while (true){
           if(task1.isDone() && task2.isDone() && task3.isDone()) {
               break;
           }
           Thread.sleep(1000);
       }
       long end = System.currentTimeMillis();
       System.out.println("任务全部完成， 总耗时：" + (end - start) + "毫秒");
   }*/

    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void test() throws Exception {
        logger.info("输出info");
        logger.debug("输出debug");
        logger.error("输出error");
    }

}
