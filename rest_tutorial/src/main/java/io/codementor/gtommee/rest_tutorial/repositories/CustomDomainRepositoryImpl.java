package io.codementor.gtommee.rest_tutorial.repositories;

import java.util.List;

//imports as static
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import java.util.List;

import io.codementor.gtommee.rest_tutorial.models.Domain;
import io.codementor.gtommee.rest_tutorial.models.HostingCount;

public class CustomDomainRepositoryImpl implements CustomDomainRepository {

    @Autowired
    MongoTemplate mongoTemplate;

	@Override
	public List<HostingCount> groupDomain() {

		Aggregation agg = newAggregation(
				match(Criteria.where("_id").lt(10)),
				group("hosting").count().as("total"),
				project("total").and("hosting").previousOperation(),
				sort(Sort.Direction.DESC, "total")					
			);

			//Convert the aggregation result into a List
			AggregationResults<HostingCount> groupResults 
				= mongoTemplate.aggregate(agg, Domain.class, HostingCount.class);
			
			List<HostingCount> result = groupResults.getMappedResults();
			
			return result;
	}
	
	@Override
	public List<Domain> groupDomain2() {

		Aggregation agg = newAggregation(
				match(Criteria.where("_id").lt(10)),
				group("hosting").count().as("total"),
				project("total").and("hosting").previousOperation(),
				sort(Sort.Direction.DESC, "total")					
			);

			AggregationResults<Domain> groupResults = mongoTemplate.aggregate(agg, Domain.class, Domain.class);
			
			List<Domain> result = groupResults.getMappedResults();
			
	    return null;
	}

	   
}