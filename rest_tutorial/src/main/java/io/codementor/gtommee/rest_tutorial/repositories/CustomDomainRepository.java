package io.codementor.gtommee.rest_tutorial.repositories;

import java.util.List;

import io.codementor.gtommee.rest_tutorial.models.Domain;
import io.codementor.gtommee.rest_tutorial.models.HostingCount;

public interface CustomDomainRepository {
	 List<HostingCount> groupDomain();
	 List<Domain> groupDomain2();
	List<Domain> groupDomain3();
}
