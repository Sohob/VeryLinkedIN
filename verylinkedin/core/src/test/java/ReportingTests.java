import org.junit.Before;

public class ReportingTests extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
//    @Test
//    public void createGroup() throws Exception {
//        String uri = "/api/v1/groups/create";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
//    @Test
//    public void deleteGroup() throws Exception {
//        String uri = "/api/v1/groups/view/{userId}/{groupId}/deletegroup";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, "20", "629761425fd39462b84486ba")
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
//    @Test
//    public void deleteMessage() throws Exception {
//        String uri = "/api/v1/groups/view/{userId}/{groupId}/deletemsg";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, "20", "629761425fd39462b84486ba")
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
//    @Test
//    public void editMessage() throws Exception {
//        String uri = "/api/v1/groups/view/{userId}/{groupId}/editmsg";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri, "20", "629761425fd39462b84486ba")
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
//    @Test
//    public void sendMessage() throws Exception {
//        String uri = "/api/v1/groups/view/{userId}/{groupId}/send";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri, "20", "629761425fd39462b84486ba")
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
//    @Test
//    public void updateGroup() throws Exception {
//        String uri = "/api/v1/groups/view/{userId}/{groupId}/updategroup";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri, "20", "629761425fd39462b84486ba")
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
//    @Test
//    public void viewGroup() throws Exception {
//        String uri = "/api/v1/groups/view/{userId}/{groupId}";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, "20", "629761425fd39462b84486ba")
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
}
