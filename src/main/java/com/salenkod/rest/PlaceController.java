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

import com.salenkod.model.PlaceTable;
import com.salenkod.pagination.PlacePaginator;
import com.salenkod.pojo.Place;
import com.salenkod.sevice.ClientService;
import com.salenkod.sevice.PlaceService;

@Controller
@RequestMapping("/resources")
public class PlaceController {

	@Autowired
	private PlaceService service;
	
	@Autowired
	private ClientService clientService;

	private Long listCount() {
		return service.getCount();
	}

	@SuppressWarnings("unchecked")
	private List<Place> findRow(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		return service.sortedFind(startPosition, maxResults, sortFields, sortDirections);
	}

	private PlacePaginator findRow(PlacePaginator wrapper) {
		wrapper.setTotalResults(listCount());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setPlaceList(findRow(start, wrapper.getPageSize(),
				wrapper.getSortFields(), wrapper.getSortDirections()));
		wrapper.setClientList(clientService.findAll());
		return wrapper;
	}

	@RequestMapping(value = "/places", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
	public @ResponseBody PlacePaginator listPlaces(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "sortFields", defaultValue = "id") String sortFields,
			@RequestParam(value = "sortDirections", defaultValue = "asc") String sortDirections) {
		PlacePaginator paginator = new PlacePaginator();
		paginator.setCurrentPage(page);
		paginator.setSortFields(sortFields);
		paginator.setSortDirections(sortDirections);
		paginator.setPageSize(10);
		return findRow(paginator);
	}

	@RequestMapping(value = "/places/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody PlaceTable getById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@RequestMapping(value = "/places", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody PlaceTable save(@RequestBody PlaceTable row) {
		if (row.getId() == null) {
			PlaceTable rowToInsert = new PlaceTable();
			rowToInsert.setAddress(row.getAddress());
			rowToInsert.setClientId(row.getClientId());
			row = service.insert(rowToInsert);
        } else {
        	PlaceTable rowToUpdate = service.findById(row.getId());
    		rowToUpdate.setAddress(row.getAddress());
    		rowToUpdate.setClientId(row.getClientId());
    		service.update(rowToUpdate);
        }
		return row;
	}

	@RequestMapping(value = "/places/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
}
