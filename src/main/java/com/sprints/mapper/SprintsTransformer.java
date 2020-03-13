package com.sprints.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sprints.domain.SprintDomain;
import com.sprints.model.Sprint;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class SprintsTransformer extends ConfigurableMapper {
	
	private MapperFacade mapperFacade;
	
	@Autowired
	public void setMapperFacade(MapperFactory mapperFactory) {
		this.mapperFacade = mapperFactory.getMapperFacade();
		mapperFactory.classMap(SprintDomain.class, Sprint.class).mapNulls(false).mapNullsInReverse(false).byDefault().register();
		mapperFactory.classMap(Sprint.class, SprintDomain.class ).mapNulls(false).mapNullsInReverse(false).byDefault().register();
	}
	
	public Sprint transformer(SprintDomain sprintDomain) {
		return mapperFacade.map(sprintDomain, Sprint.class);
	}
	
	public SprintDomain transformer(Sprint sprint) {
		return mapperFacade.map(sprint, SprintDomain.class);
	}
	
	public List<SprintDomain> listTransformer(List<Sprint> sprints){
		return mapperFacade.mapAsList(sprints, SprintDomain.class);
	}
	
}
