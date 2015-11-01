package cs542project2;

public class test_movies {
		public static void main(String args[]){
			MovieYearBTree tree = new MovieYearBTree();
			
			tree.insert(1989, "The Abyss");
			tree.insert(2001, "Amélie");
			//tree.printTree();
			tree.insert(1999, "American Beauty");
			
			tree.insert(1977,"And Mother Makes Four");
			
			tree.insert(2001,"A Beautiful Mind");
			tree.insert(1985,"Better Off Dead");
			
			tree.insert(2000,"Crouching Tiger, Hidden Dragon");
			//tree.printTree();
			tree.insert(1990,"Ghost");
			tree.insert(2000,"Gladiator");
			tree.insert(2004,"The Incredibles");
			
			
			tree.insert(2004,"The Incredibles - Filmmakers' Audio Commentaries");
			tree.insert(1977,"It's Only Money");
			tree.insert(1977,"Jack the Giant Killer");
			tree.insert(1997,"L.A. Confidential");
			tree.insert(2001,"Legally Blonde");
			
			tree.insert(2001,"The Lord of the Rings: The Fellowship of the Ring");
			tree.insert(2000,"Malena");
			tree.insert(1977,"A Man About the House");
			tree.insert(1999,"The Matrix");
			tree.insert(2001,"Moulin Rouge");
			tree.insert(2005,"Mr. & Mrs. Smith");
			tree.insert(1977,"No Children, No Pets");
			tree.insert(1994,"The One Where Monica Gets A Roommate (a.k.a. The One Where It All Began)");
			tree.insert(1994,"The One Where Nana Dies Twice");
			tree.insert(1994,"The One Where Underdog Gets Away");
			tree.insert(1994,"The One With George Stephanopoulos");
			tree.insert(1995,"The One With Mrs. Bing");
			tree.insert(1994,"The One With The Blackout");
			tree.insert(1995,"The One With The Boobies");
			tree.insert(1994,"The One With The Butt");
			tree.insert(1995,"The One With The Candy Hearts");
			tree.insert(1995,"The One With The Dozen Lasagnas");
			tree.insert(1994,"The One With The East German Laundry Detergent");
			tree.insert(1994,"The One With The Monkey");
			tree.insert(1994,"The One With The Sonogram At The End");
			tree.insert(1995,"The One With The Stoned Guy");
			tree.insert(1994,"The One With The Thumb");
			tree.insert(1995,"The One With Two Parts (1)");
			tree.insert(1990,"Pacific Heights");
			tree.insert(1977,"Roper's Niece");
			tree.insert(1998,"Saving Private Ryan");
			//tree.printTree();
			tree.insert(2004,"Shrek");
			tree.insert(1991,"The Silence of the Lambs");
			tree.insert(1987,"Wall Street");
			tree.insert(1989,"When Harry Met Sally...");

			
			//tree.printTree();
			//System.out.println(tree.search(79));
			//System.out.println(tree.search(90));
			//System.out.println(tree.search(2));
			//System.out.println(tree.search(20));
			//System.out.println(tree.search(18));
			tree.searchPrint(1997);
			
		return;
		}
	}
	class MovieYearBTree extends btree<Integer, String> {

}
