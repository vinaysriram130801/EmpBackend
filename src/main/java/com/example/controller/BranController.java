package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Branch;
import com.example.entities.Branches;
import com.example.repo.BranRepository;

@RestController
@RequestMapping("/branchservice")
@CrossOrigin(origins="*")
public class BranController {
	
	
	public BranController(BranRepository bran) {
		super();
		this.bran = bran;
	}

	private BranRepository bran;
	
	@GetMapping("/Bgetall")
	public Branches getAll() {
		Branches bs = new Branches(bran.findAll());
		return bs;
	}
	
	@CrossOrigin("http://localhost:1563")
	@GetMapping("/Bgetone/{id}")
	public Branch Bgetone(@PathVariable int id) {
		Branch b = bran.findById(id).get();
		return b;
	}
	
	@PostMapping("/Badd")
	public void Badd(@RequestBody Branch b) {
		bran.save(b);
	}
	@CrossOrigin("http://localhost:1563")
	@PutMapping("/Bupdate/{id}")
	public Branch Bupdate(@PathVariable int id, @RequestBody Branch bnew) {
		Branch b = bran.findById(id).get();
		b.setBranch_name(bnew.getBranch_name());
		b.setBranch_location(bnew.getBranch_location());
		b.setBranch_contact(bnew.getBranch_contact());
		bran.save(b);
		return b;
	}
	
	@CrossOrigin("http://localhost:1563")
	@DeleteMapping("/Bdelete/{id}")
	public void Bdelete(@PathVariable int id) {
		bran.deleteById(id);
	}
	

}
