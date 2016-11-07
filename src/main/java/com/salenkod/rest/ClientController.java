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

import com.salenkod.sevice.ClientService;
import com.salenkod.model.ClientTable;
import com.salenkod.pagination.ClientPaginator;

@Controller
@RequestMapping("/resources")
public class ClientController {

	@Autowired
	private ClientService service;

	private Long listCount() {
		return service.getCount();
	}

	@SuppressWarnings("unchecked")
	private List<ClientTable> findRow(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		return service.sortedFind(startPosition, maxResults, sortFields, sortDirections);
	}

	private ClientPaginator findRow(ClientPaginator wrapper) {
		wrapper.setTotalResults(listCount());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setClientList(findRow(start, wrapper.getPageSize(),
				wrapper.getSortFields(), wrapper.getSortDirections()));
		return wrapper;
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
	public @ResponseBody ClientPaginator listMyClientProj(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "sortFields", defaultValue = "id") String sortFields,
			@RequestParam(value = "sortDirections", defaultValue = "asc") String sortDirections) {
		ClientPaginator paginator = new ClientPaginator();
		paginator.setCurrentPage(page);
		paginator.setSortFields(sortFields);
		paginator.setSortDirections(sortDirections);
		paginator.setPageSize(10);
		return findRow(paginator);
	}

	@RequestMapping(value = "/clients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody ClientTable getById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@RequestMapping(value = "/clients", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody ClientTable save(@RequestBody ClientTable row) {
		if (row.getId() == null) {
			ClientTable rowToInsert = new ClientTable();
			rowToInsert.setName(row.getName());
			rowToInsert.setPhone(row.getPhone());
			row = service.insert(rowToInsert);
        } else {
        	ClientTable rowToUpdate = service.findById(row.getId());
    		rowToUpdate.setName(row.getName());
    		rowToUpdate.setPhone(row.getPhone());
    		service.update(rowToUpdate);
        }
		return row;
	}

	@RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
}
