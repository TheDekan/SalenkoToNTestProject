package com.salenkod.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salenkod.model.VisitTable;
import com.salenkod.pagination.VisitPaginator;
import com.salenkod.pojo.Visit;
import com.salenkod.sevice.ClientService;
import com.salenkod.sevice.PlaceService;
import com.salenkod.sevice.VisitService;

@Controller
@RequestMapping("/resources")
public class VisitController {

	@Autowired
	private VisitService service;

	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private ClientService clientService;
	
	private Long listCount() {
		return service.getCount();
	}

	@SuppressWarnings("unchecked")
	private List<Visit> findRow(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		return service.sortedFind(startPosition, maxResults, sortFields, sortDirections);
	}

	private VisitPaginator findRow(VisitPaginator wrapper) {
		wrapper.setTotalResults(listCount());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setVisitList(findRow(start, wrapper.getPageSize(),
				wrapper.getSortFields(), wrapper.getSortDirections()));
		wrapper.setPlaceList(placeService.findAll());
		wrapper.setClientList(clientService.findAll());
		return wrapper;
	}

	@RequestMapping(value = "/visits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
	public @ResponseBody VisitPaginator listVisits(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "sortFields", defaultValue = "id") String sortFields,
			@RequestParam(value = "sortDirections", defaultValue = "asc") String sortDirections) {
		VisitPaginator paginator = new VisitPaginator();
		paginator.setCurrentPage(page);
		paginator.setSortFields(sortFields);
		paginator.setSortDirections(sortDirections);
		paginator.setPageSize(10);
		return findRow(paginator);
	}

	@RequestMapping(value = "/visits/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody VisitTable getById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@RequestMapping(value = "/visits", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody VisitTable save(@RequestBody VisitTable row) {
		if (row.getId() == null) {
			VisitTable rowToinsert = new VisitTable();
			rowToinsert.setVisitTime(row.getVisitTime());
			rowToinsert.setPlaceId(row.getPlaceId());
			row = service.insert(rowToinsert);
        } else {
        	VisitTable rowToUpdate = service.findById(row.getId());
    		rowToUpdate.setVisitTime(row.getVisitTime());
    		rowToUpdate.setPlaceId(row.getPlaceId());
    		service.update(rowToUpdate);
        }
		return row;
	}

	@RequestMapping(value = "/visits/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
