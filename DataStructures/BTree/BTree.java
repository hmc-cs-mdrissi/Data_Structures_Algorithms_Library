import java.io.*;
import java.util.*;

public class BTree{
    private int filesMade = 0;
    private Node root = null;
    private ArrayList<Integer> initialData = new ArrayList<Integer>();
    public static final int capacity = 4;

    public BTree(){

    }

    private static class Node{
        //The 5th potential child node is simply to assist in splitting
        private String[] fileNames = new String[5];
        private int[] numValues = new int[5];

        //The purpose of minValues for all is for leaf nodes to keep track of the first file's minValue for nodes above it.
        private int[] minValues = new int[5];

        private Node[] children = new Node[5];

        private boolean isLeaf(){
            return children[0] == null;
        }

        private Node(Node child1, Node child2, Node child3) throws IOException{
            children[0] = child1;
            children[1] = child2;
            children[2] = child3;

    	    minValues[0] = child1.minValue();
            minValues[1] = child2.minValue();

            if(child3 != null){
                minValues[2] = child3.minValue();
            }
        }

        private Node(String name1, int numValue1, String name2, int numValue2, String name3, int numValue3) throws IOException{
            fileNames[0] = name1;
            numValues[0] = numValue1;
            minValues[0] = minValue(name1);

            fileNames[1] = name2;
            numValues[1] = numValue2;
            minValues[1] = minValue(name2);

            if(name3 != null){
                fileNames[2] = name3;
                numValues[2] = numValue3;
                minValues[2] = minValue(name3);
            }
        }

        private int minValue() throws IOException{
            if(!isLeaf()){
                return children[0].minValue();
            } else {
                return minValues[0];
            }
        }

        private static int minValue(String fileName) throws IOException{
            Scanner input = new Scanner(new File(fileName));

            int min = Integer.MAX_VALUE;

            while(input.hasNext()){
                int curr = input.nextInt();

                min = Math.min(min,curr);
            }

            input.close();

            return min;
        }

        public int hashCode(){
            return minValues[0]*16 + minValues[1]*8 + minValues[2]*4 + minValues[3]*2 + minValues[4];
        }

        public boolean equals(Object other){
            if(other != null && other instanceof Node){
                Node toCompare = (Node) other;
                return this.minValues[0] == toCompare.minValues[0] && this.minValues[1] == toCompare.minValues[1] && this.minValues[2] == toCompare.minValues[2] && this.minValues[3] == toCompare.minValues[3] && this.minValues[4] == toCompare.minValues[4];
            }

            return false;
        }
    }

    public void add(int element) throws IOException{
        if(root == null){
            initialData.add(element);

            if(initialData.size() > capacity){
                Collections.sort(initialData);

                String name1 = "file" + filesMade + ".txt";
                filesMade++;

                String name2 = "file" + filesMade + ".txt";
                filesMade++;

                addToFile(name1,initialData,0,capacity/2);
                addToFile(name2,initialData,capacity/2,capacity+1);

                root = new Node(name1,capacity/2,name2,capacity/2+1,null,0);
            }
        } else {
            //If you have a B-Tree with height of 50 or more then the stack size will have to be increased.
            myStack<Node> path = new myStack<Node>(50);
            Node curr = root;
            path.push(curr);

            while(!curr.isLeaf()){
                int place = 1;

                for(; place < 4; place++){
                    if(curr.children[place] == null){
                        break;
                    } else if(curr.minValues[place] > element){
                        break;
                    }
                }

                curr = curr.children[place-1];
                path.push(curr);
            }

            int place = 1;

            for(; place < 4; place++){
                    if(curr.fileNames[place] == null){
                        break;
                    } else if(curr.minValues[place] > element){
                        break;
                    }
            }

            place--;

			System.out.println(element + " is being added to " + curr.fileNames[place] + " and is the " + (curr.numValues[place]+1) + " element.");

            PrintWriter appendNumber = new PrintWriter(new BufferedWriter(new FileWriter(curr.fileNames[place],true)));

            appendNumber.println(element);
            appendNumber.close();

            curr.numValues[place]++;

            /*With the way files are reached to place a value, this should only change the minimum when a value smaller than the smallest appears.*/
            curr.minValues[place] = Math.min(curr.minValues[place],element);

            if(curr.numValues[place] > capacity){
            	System.out.println("A split has had to occur.");
                ArrayList<Integer> values = new ArrayList<Integer>(capacity+1);
                Scanner input = new Scanner(new File(curr.fileNames[place]));

                while(input.hasNextInt()){
                    values.add(input.nextInt());
                }

                input.close();

                Collections.sort(values);
                addToFile(curr.fileNames[place],values,0,capacity/2);
                curr.numValues[place] = capacity/2;

                place++;

                if(curr.fileNames[place] != null){
                    String currFile = curr.fileNames[place];
                    int currMin = curr.minValues[place];
                    int currSize = curr.numValues[place];

                    String newFile = "file" + filesMade + ".txt";
                    filesMade++;
                    addToFile(newFile,values,capacity/2,capacity+1);

                    curr.fileNames[place] = newFile;
                    curr.minValues[place] = values.get(capacity/2);
                    curr.numValues[place] = capacity/2+1;

                    place++;

                    for(; place < 5; place++){
                        if(curr.fileNames[place] != null){
                            String nextFile = curr.fileNames[place];
                            int nextMin = curr.minValues[place];
                            int nextSize = curr.numValues[place];

                            curr.fileNames[place] = currFile;
                            curr.minValues[place] = currMin;
                            curr.numValues[place] = currSize;

                            currFile = nextFile;
                            currMin = nextMin;
                            currSize = nextSize;

                        } else {
                            curr.fileNames[place] = currFile;
                            curr.minValues[place] = currMin;
                            curr.numValues[place] = currSize;
                            break;
                        }
                    }
                } else {
                    String newFile = "file" + filesMade + ".txt";
                    filesMade++;
                    addToFile(newFile,values,capacity/2,capacity+1);

                    curr.fileNames[place] = newFile;
                    curr.minValues[place] = values.get(capacity/2);
                    curr.numValues[place] = capacity/2+1;
                }

                while(path.size() > 1){
                        Node currNode = path.pop();
                        Node parent = path.peek();

                        if(currNode.fileNames[4] != null || currNode.children[4] != null){
                           splitNode(currNode,parent);
                        }
                }

                if(root.fileNames[4] != null || root.children[4] != null){
                     splitRoot();
                }
            }
        }
    }

    private static void addToFile(String fileName, ArrayList<Integer> values, int start, int end) throws IOException{
        PrintWriter file = new PrintWriter(fileName);

        for(int i = start; i < end; i++){
            file.println(values.get(i));
        }

		file.close();
    }

    private void splitNode(Node toSplit, Node parent) throws IOException{
        int nodePlace = findPlacing(parent,toSplit);
		Node newParent1;
		Node newParent2;

	if(toSplit.isLeaf()){
		System.out.println("A leaf node is being split.");
		newParent1 = new Node(toSplit.fileNames[0],toSplit.numValues[0],toSplit.fileNames[1],toSplit.numValues[1],toSplit.fileNames[2],toSplit.numValues[2]);
		newParent2 = new Node(toSplit.fileNames[3],toSplit.numValues[3],toSplit.fileNames[4],toSplit.numValues[4],null,0);
	} else {
		System.out.println("A body node is being split.");
		newParent1 = new Node(toSplit.children[0], toSplit.children[1], toSplit.children[2]);
		newParent2 = new Node(toSplit.children[3],toSplit.children[4], null);
	}

	parent.children[nodePlace] = newParent1;
	nodePlace++;

	if(parent.children[nodePlace] == null){
		parent.children[nodePlace] = newParent2;
		parent.minValues[nodePlace] = newParent2.minValue();
	} else {
		Node currNode = parent.children[nodePlace];
		int currMinValue = parent.minValues[nodePlace];

		parent.children[nodePlace] = newParent2;
		parent.minValues[nodePlace] = newParent2.minValue();

		nodePlace++;

		for(; nodePlace < 5; nodePlace++){
			if(parent.children[nodePlace] != null){
				Node nextNode = parent.children[nodePlace];
				int nextMinValue = parent.minValues[nodePlace];

				parent.children[nodePlace] = currNode;
				parent.minValues[nodePlace] = currMinValue;

				currNode = nextNode;
				currMinValue = nextMinValue;
			} else {
				parent.children[nodePlace] = currNode;
				parent.minValues[nodePlace] = currMinValue;
				break;
			}
		}
	}
    }

    private void splitRoot() throws IOException{
    	Node newParent1;
    	Node newParent2;

		if(!root.isLeaf()){
			System.out.println("A body root split is occurring.");
			newParent1 = new Node(root.children[0], root.children[1], root.children[2]);
			newParent2 = new Node(root.children[3],root.children[4],null);
		} else {
			System.out.println("A leaf root split is occurring.");
			newParent1 = new Node(root.fileNames[0],root.numValues[0],root.fileNames[1],root.numValues[1],root.fileNames[2],root.numValues[2]);
			newParent2 = new Node(root.fileNames[3],root.numValues[3],root.fileNames[4],root.numValues[4],null,0);
		}

		root = new Node(newParent1,newParent2,null);
    }

    private int findPlacing(Node par, Node toFind){
        for(int i = 0; i < 4; i++){
            if(par.children[i].equals(toFind)){
                return i;
            }
        }

        return -1;
    }

    public boolean contains(int element) throws IOException{
    	if(root == null){
    		return initialData.contains(element);
    	}

    	Node curr = root;

    	while(!curr.isLeaf()){
                int place = 1;

                for(; place < 4; place++){
                    if(curr.children[place] == null){
                        break;
                    } else if(curr.minValues[place] > element){
                        break;
                    }
                }

                curr = curr.children[place-1];
        }

        int place = 1;

        for(; place < 4; place++){
              if(curr.fileNames[place] == null){
                    break;
              } else if(curr.minValues[place] > element){
                    break;
              }
        }

  		for(int i = 0; i < 5; i++){
  			if(curr.fileNames[i] != null){
  				System.out.println("One of the node's children is " + curr.fileNames[i]);
  				Scanner in = new Scanner(new File(curr.fileNames[i]));
  				System.out.print("The elements the child contains are ");

  				while(in.hasNextInt()){
  					System.out.print(in.nextInt() + " ");
  				}

  				in.close();

  				System.out.println(". That child's miniumum value is " + curr.minValues[i] + ". It contains " + curr.numValues[i] + " numbers.");
  			} else {
  				System.out.println("There is no child at position " + (i+1));
  			}
  		}

		System.out.println("Checking file " + curr.fileNames[place-1]);

        Scanner input = new Scanner(new File(curr.fileNames[place-1]));

        while(input.hasNextInt()){
        	int i = input.nextInt();

        	if(i == element){
        		input.close();
        		return true;
        	}
        }

		input.close();
        return false;

    }
}