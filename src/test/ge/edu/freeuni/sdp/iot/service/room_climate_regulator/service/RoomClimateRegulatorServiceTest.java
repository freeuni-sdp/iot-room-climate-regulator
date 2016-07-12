package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.service;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data.FakeRepository;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.Task;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy.*;
import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RoomClimateRegulatorServiceTest extends JerseyTest {

    @Mock
    private AirConditioningSwitchProxy airConditioningSwitchProxy;
    @Mock
    private HeatingSwitchProxy heatingSwitchProxy;
    @Mock
    private HouseRegistryServiceProxy houseRegistryServiceProxy;
    @Mock
    private HumanAtHomeServiceProxy humanAtHomeServiceProxy;
    @Mock
    private RoomThermometerProxy roomThermometerProxy;

    @Override
    protected Application configure() {
        return new ResourceConfig(FakeRoomClimateRegulatorService.class);
    }

    @Before
    public void setUpChild() throws Exception {
        MockitoAnnotations.initMocks(this);
        FakeProxyFactory.instance().setAirConditioningSwitchProxy(airConditioningSwitchProxy);
        FakeProxyFactory.instance().setHeatingSwitchProxy(heatingSwitchProxy);
        FakeProxyFactory.instance().setHouseRegistryServiceProxy(houseRegistryServiceProxy);
        FakeProxyFactory.instance().setHumanAtHomeServiceProxy(humanAtHomeServiceProxy);
        FakeProxyFactory.instance().setRoomThermometerProxy(roomThermometerProxy);
    }

    @Test
    public void testUpdate_except200CreateTask() {
        FakeRepository.instance().clear();
        String houseId = "1";
        String floorId = "1";
        when(houseRegistryServiceProxy.get(houseId)).thenReturn(true);
        Task expected = getRandomTask(houseId, floorId);
        Response actual = getTarget(houseId, floorId)
                .request()
                .put(Entity.entity(expected.toDo(), MediaType.APPLICATION_JSON_TYPE));
        assertThat(actual.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(FakeRepository.instance().contains(expected), is(true));
    }

    @Test
    public void testUpdate_except200UpdateTask() {
        FakeRepository.instance().clear();
        String houseId = "1";
        String floorId = "1";
        when(houseRegistryServiceProxy.get(houseId)).thenReturn(true);
        Task randomTask = getRandomTask(houseId, floorId);
        getTarget(houseId, floorId)
                .request()
                .put(Entity.entity(randomTask.toDo(), MediaType.APPLICATION_JSON_TYPE));
        Task expected = getRandomTask(houseId, floorId);
        Response actual = getTarget(houseId, floorId)
                .request()
                .put(Entity.entity(expected.toDo(), MediaType.APPLICATION_JSON_TYPE));
        assertThat(actual.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(FakeRepository.instance().contains(expected), is(true));
        assertThat(FakeRepository.instance().contains(randomTask), is(false));
    }

    @Test
    public void testUpdate_except200TwoTasks() {
        FakeRepository.instance().clear();
        String houseId1 = "1";
        String floorId1 = "1";
        when(houseRegistryServiceProxy.get(houseId1)).thenReturn(true);
        Task expected1 = getRandomTask(houseId1, floorId1);
        Response actual1 = getTarget(houseId1, floorId1)
                .request()
                .put(Entity.entity(expected1.toDo(), MediaType.APPLICATION_JSON_TYPE));
        String houseId2 = "2";
        String floorId2 = "1";
        when(houseRegistryServiceProxy.get(houseId2)).thenReturn(true);
        Task expected2 = getRandomTask(houseId2, floorId2);
        Response actual2 = getTarget(houseId2, floorId2)
                .request()
                .put(Entity.entity(expected2.toDo(), MediaType.APPLICATION_JSON_TYPE));
        List<Task> actual = Lists.newArrayList(FakeRepository.instance().getAll());
        List<Task> expected = Arrays.asList(expected1, expected2);
        assertThat(actual1.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(actual2.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(FakeRepository.instance().contains(expected1), is(true));
        assertThat(FakeRepository.instance().contains(expected2), is(true));
        assertThat(expected.containsAll(actual) && actual.containsAll(expected), is(true));
    }

    @Test
    public void testUpdate_except404WrongHouseId() {
        FakeRepository.instance().clear();
        String houseId = "1";
        String floorId = "1";
        when(houseRegistryServiceProxy.get(houseId)).thenReturn(false);
        Task expected = getRandomTask(houseId, floorId);
        Response actual = getTarget(houseId, floorId)
                .request()
                .put(Entity.entity(expected.toDo(), MediaType.APPLICATION_JSON_TYPE));
        assertThat(actual.getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
    }

    private WebTarget getTarget(String houseId, String floorId) {
        String path = String.format("/houses/%s/floors/%s/task", houseId, floorId);
        return target(path);
    }

    private Task getRandomTask(String houseId, String floorId) {
        Task task = new Task();
        task.setTemperature(getRandomTemperature());
        task.setTemperature(getRandomPeriod());
        task.setStarted(false);
        task.setStartTime(0);
        task.setFloorId(floorId);
        task.setHouseId(houseId);
        return task;
    }

    private static double getRandomPeriod() {
        return new Random().nextInt(20) * 1000;
    }

    private static double getRandomTemperature() {
        return 25 + (35 - 25) * new Random().nextDouble();
    }
}