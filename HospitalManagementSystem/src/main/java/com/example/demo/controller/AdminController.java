package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.service.Doctorservice;
import com.example.demo.service.Patientservice;

@RequestMapping("/Admin")
@Controller
public class AdminController {
		@Autowired 
		private Doctorservice service;
		
		@Autowired
		private Patientservice pservice;
		
	
		@RequestMapping("/Home")
	    public String Home(){
	    		return "adminhome";
	    }
		@RequestMapping("/Patient")
	    public String Patient(){
	    		return "Patient";
	    }
	    @RequestMapping("/AddDoctor")
	    public String AddDoctor(Model model){
	    		model.addAttribute("doctor",new Doctor());
	    		return "AddDoctor";
	    }
	    @RequestMapping("/AddPatient")
	    public String AddPatient(Model model){
	    		model.addAttribute("patient",new Patient());
	    		return "AddPatient";
	    }
	    @RequestMapping("/Doctorlist")
	    public String 	Doctorlistpage(Model model){
	    	List<Doctor> listdoctor = service.listall();
			model.addAttribute("listdoctor",listdoctor);
	    		return "Doctorlist";
	    }
	    @RequestMapping("/Patientlist")
	    public String 	Patientlistpage(Model model){
	    	List<Patient> listpatient = pservice.listall();
			model.addAttribute("listpatient",listpatient);
	    		return "Patientlist";
	    }
	    @RequestMapping("/edit/{id}")
		public ModelAndView showEditDoctorPage(@PathVariable(name = "id")int id)
		{
			ModelAndView mav = new ModelAndView("edit_doctor");
			Doctor doctor = service.get(id);
			mav.addObject("doctor",doctor);
			return mav;
		}
	    @RequestMapping("/editp/{id}")
		public ModelAndView showEditPatientPage(@PathVariable(name = "id")int id)
		{
			ModelAndView mav = new ModelAndView("edit_patient");
			Patient patient = pservice.get(id);
			mav.addObject("patient",patient);
			return mav;
		}
	    @RequestMapping("/deletep/{id}")
		public String deletePatient(@PathVariable(name="id")int id)
		{
			pservice.delete(id);
			return "redirect:/Admin/Patientlist";
		}
	    @RequestMapping("/delete/{id}")
		public String deletedoctor(@PathVariable(name="id")int id)
		{
			service.delete(id);
			return "redirect:/Admin/Doctorlist";
		}
}
