package com.abernathy.mediscreen.mnotes.repository;

import com.abernathy.mediscreen.mnotes.model.TriggersCountResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import net.bytebuddy.utility.RandomString;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentRepositoryTest {

    @InjectMocks
    private AssessmentRepository repositoryUnderTest;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private MongoCollection<Document> collection;

    @Mock
    private AggregateIterable<Document> documents;

    @Mock
    private ObjectMapper mapper;

    Random random = new Random();

    @Test
    void getTriggersCountWithNoTriggersMatchTest()  {
        // Arrange
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        when(mongoTemplate.getDb()).thenReturn(mockDatabase);
        when(mockDatabase.getCollection(any())).thenReturn(collection);
        when(collection.aggregate(any())).thenReturn(documents);
        when(documents.into(any())).thenReturn(new ArrayList<>());
        // Act
        int resultTriggersCount = repositoryUnderTest.getTriggersCount(1, "kk");
        // Assert
        assertThat(resultTriggersCount).isZero();
    }

    @Test
    void getTriggersCountWithTriggersMatchTest() throws JsonProcessingException {
        // Arrange
        int testTotal = random.nextInt();

        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        when(mongoTemplate.getDb()).thenReturn(mockDatabase);
        when(mockDatabase.getCollection(any())).thenReturn(collection);
        when(collection.aggregate(any())).thenReturn(documents);
        List<String> aa = new ArrayList<>();
        aa.add("something");
        when(documents.into(any())).thenReturn(aa);
        when(documents.first()).thenReturn(new Document());

        TriggersCountResult expectedResult = new TriggersCountResult();
        expectedResult.setTotal(testTotal);
        when(mapper.readValue(anyString(), ArgumentMatchers.<Class<TriggersCountResult>>any())).thenReturn(expectedResult);

        // Act
        int resultTriggersCount = repositoryUnderTest.getTriggersCount(random.nextInt(), RandomString.make(64));

        // Assert
        assertThat(resultTriggersCount).isEqualTo(testTotal);
    }

    @Test
    void getTriggersCountWithJsonProcessingExceptionTest() throws JsonProcessingException {
        // Arrange
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        when(mongoTemplate.getDb()).thenReturn(mockDatabase);
        when(mockDatabase.getCollection(any())).thenReturn(collection);
        when(collection.aggregate(any())).thenReturn(documents);
        List<String> aa = new ArrayList<>();
        aa.add("something");
        when(documents.into(any())).thenReturn(aa);
        when(documents.first()).thenReturn(new Document());

        when(mapper.readValue(anyString(), ArgumentMatchers.<Class<TriggersCountResult>>any())).thenThrow(JsonProcessingException.class);

        // Act
        int resultTriggersCount = repositoryUnderTest.getTriggersCount(random.nextInt(), RandomString.make(64));

        // Assert
        assertThat(resultTriggersCount).isZero();
    }


}
