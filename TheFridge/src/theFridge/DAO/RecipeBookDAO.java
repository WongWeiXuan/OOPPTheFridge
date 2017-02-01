package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.RecipeBook;

public class RecipeBookDAO {
	private static final String RecipeBookList_File="RecipeBookList.txt";
	private File dataFile;
	 
	
	public RecipeBookDAO() {
		Path dPath = FileSystems.getDefault().getPath("resources/",RecipeBookList_File);
     	dataFile=new File(dPath.toString());     
	}
	
	//public static ArrayList<Friend> getAllFriends() {
	//	FriendDAO friendDao=new FriendDAO();
	//	return friendDao.getAllFriends();
	//}

	public ArrayList<RecipeBook> getAllRecipes() {
		Scanner in;
		String record=null;
		String[] fields;
		ArrayList<RecipeBook> RecipeBooks=new ArrayList<RecipeBook>();
		try {
			in=new Scanner(dataFile);
			while (in.hasNextLine()) {
				record=in.nextLine();
				System.out.println("Record:" + record);
				fields=record.split(";");
				String addedBy=fields[0];
				String recipeName=fields[1];
				String ingredient1=fields[2];
				String ingredient2=fields[3];
				String ingredient3=fields[4];
				String steps=fields[5];
				
				RecipeBook f=new RecipeBook(addedBy,recipeName,ingredient1,ingredient2,ingredient3,steps);
				RecipeBooks.add(f);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return RecipeBooks;
	}

	/* assuming RecipeBook has a unique name, otherwise may return a list */
	public RecipeBook getRecipeBook(String name) {
		
		ArrayList<RecipeBook> RecipeBooks=getAllRecipes();
		RecipeBook RecipeBook=null;
		for (RecipeBook f:RecipeBooks) {
			if (f.getRecipeName().equals(name)){
				RecipeBook=f;
				break;
			}
		}
		return RecipeBook;
	}

	public void updateRecipeBook(RecipeBook RecipeBook) {
		ArrayList<RecipeBook> RecipeBooks=getAllRecipes();
		for (int i=0; i<RecipeBooks.size(); i++) {
			RecipeBook f=RecipeBooks.get(i);
			if (f.getRecipeName().equals(RecipeBook.getRecipeName())){
				RecipeBooks.set(i, RecipeBook);
			}
		}
		synToFile(RecipeBooks);
	}
	 
	public void deleteRecipeBook(RecipeBook RecipeBook) {
		
		ArrayList<RecipeBook> RecipeBooks=getAllRecipes();
		
		RecipeBook delRecipeBook=null;
		for (RecipeBook f:RecipeBooks) {
			if (RecipeBook.getRecipeName().equals(f.getRecipeName())){
				delRecipeBook=f;			
				break;
			}
		}
		if (delRecipeBook!=null){
		    RecipeBooks.remove(delRecipeBook);
			synToFile(RecipeBooks);		
		}		
	
	}
 
	public boolean createRecipeBook(RecipeBook RecipeBook) {
		boolean existing=false;
		ArrayList<RecipeBook> RecipeBooks=getAllRecipes();
		for (RecipeBook f:RecipeBooks) {
			if (f.getRecipeName().equals(RecipeBook.getRecipeName())){
				existing=true;
				break;
			}
		}
		if (!existing) {
			RecipeBooks.add(RecipeBook);
			synToFile(RecipeBooks);
		}
		return !existing;
	}

	public void synToFile(ArrayList<RecipeBook> RecipeBookList) {
		if (RecipeBookList==null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (RecipeBook f: RecipeBookList) {
				out.append(f.toString()+"\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String args[]) {
		
		RecipeBookDAO RecipeBookDao=new RecipeBookDAO();
		
		//create RecipeBook 
		
		/*
		System.out.println("\nCreate RecipeBook Spicy Fried Murloc Tails ");
		System.out.println("=============================");
		RecipeBook f=new RecipeBook("mudkip","Fried Murloc Tails","Murloc Tails","Chili","Palm Oil","Heat up the pan with oil before searing the tails until golden green. Add chili afterwards");
		RecipeBookDao.createRecipeBook(f);
		
			f=new RecipeBook("mudkip","Boiled Deathwings","Deathwing Fillet","Garlic","Onions","Add Deathwing fillet into boiling water for 1 week to soften. Add in generous amounts of garlic and Onion afterwards and boil for 2 days to cook");
		System.out.println("\nCreate RecipeBook Boiled Deathwings");
		System.out.println("=============================");
		RecipeBookDao.createRecipeBook(f);
		
		System.out.println("\nGet all RecipeBooks");
		System.out.println("=============================");
		ArrayList<RecipeBook> list=RecipeBookDao.getAllRecipes();
		for (RecipeBook RecipeBook:list) {
			System.out.println("Added by: "+RecipeBook.getAddedBy());
			System.out.println("Recipe Name: "+RecipeBook.getRecipeName());
			System.out.println("Ingredient 1: "+RecipeBook.getIngredient1());
			System.out.println("Ingredient 2: "+RecipeBook.getIngredient2());
			System.out.println("Ingredient 3: "+RecipeBook.getIngredient3());
			System.out.println("Steps: "+RecipeBook.getSteps());
		}
		
		
		System.out.println("\nGet RecipeBook Boiled Deathwings");
		System.out.println("=============================");
		
		f=RecipeBookDao.getRecipeBook("Boiled Deathwings");
		System.out.println("Added by: "+f.getAddedBy());
		System.out.println("Recipe Name: "+f.getRecipeName());
		System.out.println("Ingredient 1: "+f.getIngredient1());
		System.out.println("Ingredient 2: "+f.getIngredient2());
		System.out.println("Ingredient 3: "+f.getIngredient3());
		System.out.println("Steps: "+f.getSteps());
		
		System.out.println("\nUpdate RecipeBook Ah Hua");
		System.out.println("=============================");
		RecipeBookDao.updateRecipeBook(f);
		
		System.out.println("\nGet all RecipeBooks again");
		System.out.println("=============================");
		list=RecipeBookDao.getAllRecipes();
			for (RecipeBook RecipeBook:list) {
				System.out.println("Added by: "+RecipeBook.getAddedBy());
				System.out.println("Recipe Name: "+RecipeBook.getRecipeName());
				System.out.println("Ingredient 1: "+RecipeBook.getIngredient1());
				System.out.println("Ingredient 2: "+RecipeBook.getIngredient2());
				System.out.println("Ingredient 3: "+RecipeBook.getIngredient3());
				System.out.println("Steps: "+RecipeBook.getSteps());
			}
		
		f=new RecipeBook("mudkip","Fried Murloc Tails","Murloc Tails","Chili","Palm Oil","Heat up the pan with oil before searing the tails until golden green. Add chili afterwards");
		RecipeBookDao.deleteRecipeBook(f); 
		
		f=new RecipeBook("mudkip","Boiled Deathwings","Deathwing Fillet","Garlic","Onions","Add Deathwing fillet into boiling water for 1 week to soften. Add in generous amounts of garlic and Onion afterwards and boil for 2 days to cook");
		RecipeBookDao.deleteRecipeBook(f);
		
		list=RecipeBookDao.getAllRecipes();
		for (RecipeBook RecipeBook:list) {
			System.out.println("Added by: "+RecipeBook.getAddedBy());
			System.out.println("Recipe Name: "+RecipeBook.getRecipeName());
			System.out.println("Ingredient 1: "+RecipeBook.getIngredient1());
			System.out.println("Ingredient 2: "+RecipeBook.getIngredient2());
			System.out.println("Ingredient 3: "+RecipeBook.getIngredient3());
			System.out.println("Steps: "+RecipeBook.getSteps());
		}
		
		*/
	}
}
