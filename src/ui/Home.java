package ui;

import java.util.ArrayList;

import problemDomain.Project;

/**
 * The main interface through which the owner interacts with the system
 *
 */
public class Home {

	/**
	 * default Home constructor
	 */
	public Home(){
		
	}
	
	/**
	 * displays the project frame for the passed argument
	 * @param project the project to display on screen
	 */
	public void displayProject(Project project){
		
	}
	
	/**
	 * displays the client frame for the passed argument
	 * @param searchString a delimited string limiting which clients show up in the frame
	 */
	public void displayClient(String searchString){
		
	}
	
	/**
	 * calls the controller to navigate to the correct page
	 * @param page to be navigated to
	 */
	public void navigateTo(String page){
		
	}
	
	
	/**
	 * filters projects seen on the page
	 * @param search search string to filter with
	 * @param projects projects to be filtered
	 */
	public void searchProject(String search, ArrayList projects){
		
	}
	
	/**
	 * creates a new client
	 */
	public void createClient(){
		
	}
	
	/**
	 * filters clients seen on the page
	 * @param search search string to filter with
	 * @param clients clients to be filtered
	 */
	public void searchClient(String search, ArrayList clients){
		
	}
	/**
	 * performs a backup command on the database
	 */
	public void backup(){
		
	}
	/**
	 * filters labourers seen on the page
	 * @param search search string to filter with
	 * @param labourers labourers to be filtered
	 */
	public void searchLabourer(String search, ArrayList labourers){
		
	}
	/**
	 * creates a new labourer
	 */
	public void createLabourer(){
		
	}
	/**
	 * performs a restore command on the database
	 */
	public void restore(){
		
	}
	
}
