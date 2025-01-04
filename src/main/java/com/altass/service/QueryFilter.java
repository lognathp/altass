package com.altass.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class QueryFilter {

	private Map<String, Object> filters = new HashMap<>();
	private String sortField = "overallExperience";
	private boolean descending = true;
	private int limit = 10;
	private int skip = 0;

	public QueryFilter sortBy(String sortField, boolean descending) {
		this.sortField = sortField;
		this.descending = descending;
		return this;
	}

	public QueryFilter limit(int limit) {
		this.limit = limit;
		return this;
	}

	public QueryFilter skip(int skip) {
		this.skip = skip;
		return this;
	}

	public Query buildQuery() {
		Query query = new Query();
		filters.forEach((key, value) -> {
			query.addCriteria(Criteria.where(key).is(value));
		});

		if (descending) {
			query.with(Sort.by(Sort.Order.desc(sortField)));
		} else {
			query.with(Sort.by(Sort.Order.asc(sortField)));
		}

		query.skip(skip);
		query.limit(limit);
		System.out.print(query);
		return query;
	}
}
