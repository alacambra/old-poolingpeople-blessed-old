package poolingpeople.webapplication.business.task.boundary;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.persistence.neo4j.entities.PersistedTask;
import poolingpeople.webapplication.business.task.entity.TaskDTO;

public class TaskBoundaryOldTest {

    TaskBoundary cut;

    @Mock
    ObjectMapper objectMapperMock;

    @Mock
    EntityFactory entityFactoryMock;

    @Mock
    PersistedTask persistedTaskMock;

    @Mock
    ObjectWriter objectWritterMock;

    @Mock
    List<PersistedTask> persistedTaskListMock;

    @Mock
    TaskDTO taskDtoMock;

    @Before
    public void setUp() throws IOException {
//        MockitoAnnotations.initMocks(this);
//        cut = new TaskBoundary();
//
//        cut.dtoConverter = dtoConverterMock;
//        cut.entityFactory = entityFactoryMock;
//        cut.mapper = objectMapperMock;

    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void testGetTaskById() throws Exception {
//        when(objectMapperMock.writerWithView(TaskMixin.class)).thenReturn(objectWritterMock);
//        when(entityFactoryMock.getTaskById("1")).thenReturn(persistedTaskMock);
//        when(objectWritterMock.writeValueAsString(persistedTaskMock)).thenReturn("{}");
//        assertEquals(Response.Status.OK, cut.getTaskById("1").getStatusInfo());
//        verify(entityFactoryMock, times(1)).getTaskById("1");
//        verify(objectMapperMock, times(1)).writerWithView(TaskMixin.class);
//        verify(objectWritterMock, times(1)).writeValueAsString(persistedTaskMock);
//    }
//
//    @Test
//    public void testGetAllTask() throws Exception {
//        when(entityFactoryMock.getAllTask()).thenReturn(persistedTaskListMock);
////        when(objectMapperMock.writerWithView(View.SampleView.class)).thenReturn(objectWritterMock);
//        when(objectWritterMock.writeValueAsString(persistedTaskListMock)).thenReturn("{}");
//        assertEquals(Response.Status.OK, cut.getAllTask().getStatusInfo());
//        verify(entityFactoryMock, times(1)).getAllTask();
////        verify(objectMapperMock, times(1)).writerWithView(View.SampleView.class);
//        verify(objectWritterMock, times(1)).writeValueAsString(persistedTaskListMock);
//    }
//
//    @Test
//    public void testSaveTask() throws Exception {
//        when(objectMapperMock.readValue("", TaskDTO.class)).thenReturn(taskDtoMock);
//        when(entityFactoryMock.createTask()).thenReturn(persistedTaskMock);
//        when(dtoConverterMock.fromDTOtoPersitedBean(taskDtoMock, entityFactoryMock.createTask())).thenReturn(persistedTaskMock);
//        when(objectWritterMock.writeValueAsString(persistedTaskMock)).thenReturn("{}");
//        assertEquals(Response.Status.OK, cut.saveTask("").getStatusInfo());
//        verify(objectMapperMock, times(1)).readValue("", TaskDTO.class);
//        verify(entityFactoryMock, times(2)).createTask();
//        verify(dtoConverterMock, times(1)).fromDTOtoPersitedBean(taskDtoMock, entityFactoryMock.createTask());
//        verify(objectMapperMock, times(1)).writeValueAsString(persistedTaskMock);
//    }
//
//    @Test
//    public void testUpdateTask() throws Exception {
//        when(objectMapperMock.readValue("{}", TaskDTO.class)).thenReturn(taskDtoMock);
//        when(entityFactoryMock.getTaskById("1")).thenReturn(persistedTaskMock);
//        when(dtoConverterMock.fromDTOtoPersitedBean(taskDtoMock, entityFactoryMock.getTaskById("1"))).thenReturn(persistedTaskMock);
//        when(objectMapperMock.writeValueAsString(persistedTaskMock)).thenReturn("{}");
//        assertEquals(Response.Status.OK, cut.updateTask("1", "{}").getStatusInfo());
//        verify(objectMapperMock, times(1)).readValue("{}", TaskDTO.class);
//        verify(entityFactoryMock, times(2)).getTaskById("1");
//        verify(dtoConverterMock, times(1)).fromDTOtoPersitedBean(taskDtoMock, entityFactoryMock.getTaskById("1"));
//        verify(objectMapperMock, times(1)).writeValueAsString(persistedTaskMock);
//    }
//
//    @Test
//    public void testDeleteTask() throws Exception {
//        assertEquals(Response.Status.OK, cut.deleteTask(("42")).getStatusInfo());
//        verify(entityFactoryMock, times(1)).deleteTask("42");
//    }

}
