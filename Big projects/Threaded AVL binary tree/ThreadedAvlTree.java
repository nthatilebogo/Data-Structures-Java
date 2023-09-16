public class ThreadedAvlTree<T extends Comparable<T>> {
    public Node<T> root;

    public ThreadedAvlTree() {
        this.root = null;
    }


    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    static Node getLeftMost(Node node) {
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }

    // Inorder traversal of a threaded avl tree
    void print(Node<T> node) {
        if (node == null)
            return;

        Node<T> cur = getLeftMost(node);

        while (cur != null) {
            System.out.print(" " + cur.data + " ");


            if (cur.rightThread == true)
                cur = cur.right;
            else 
                cur = getLeftMost(cur.right);
        }
    }

    /* Do not edit the code above */


    void convertAVLtoThreaded(Node<T> node) {
    	this.root = node;
    	inorder_conv(node);
    	
    }

    /**
     * Insert the given data into the tree.
     * Duplicate data is not allowed. just return the node.
     */


    Node<T> insert(Node<T> node, T data) {
    	System.out.println("inserting " + data);
    	Node<T> nNode = new Node<T>(data);
    	if (!find_node(data)) {		// can now insert node 
			//create new node
    		ins(nNode);
		} else {		//duplicate found
			return node;
		}
    	//update node after insert
    	//System.out.println("node: " + nNode.data);
    	update_h(nNode);
    	
    	node = root;
    	
    	return node;
    }

    /**
     * Delete the given element \texttt{data} from the tree.  Re-balance the tree after deletion.
     * If the data is not in the tree, return the given node / root.
     */
    Node<T> removeNode(Node<T> root, T data) {
    	
    	
    	// Delete by copy
    	//System.out.println("removing " + data);
    	Node<T> parent = del_c(data);
    	//System.out.println("parent: " + parent.data);
    	
    	//update node
    	update_d(parent);
    	root = this.root;
    	return root;
    }
    
    //helper functions
    public Node<T> find_pred2(Node<T> node) {
    	boolean found = false;
    	Node<T> cur = root; 
    	Node<T> pred = null; 
    	if (cur == null) {
			return cur;
		}
    	
    	if (node.left != null) {	//predecessor exists
    		cur = node.left;
		} else {	//predecessor does not exists
			return pred;
		}
    	
    	//find right-most node
    	
    	while (!found) {
			if(cur.right == null) {	// found right-most node predecessor
				return cur;
			} else {
				//go to right child 
				cur = cur.right;
				if (cur.rightThread != true) {
					cur = cur.right;
				}else {
					return cur;
				}
			}//else
		}//while
    	
    	return cur;
    }
    
    
    void inorder_conv(Node<T> node) {
        if (node == null)
            return;

        inorder_conv(node.left);

        //find predecessor node
        Node<T> pred = find_pred2(node);
        if (pred != null) {	//predecessor exists
			if (pred.right == null) {
				pred.right = node;
				pred.rightThread = true;
			}
		}
        

        inorder_conv(node.right);
    }
    void inorder_unconv(Node<T> node) {
        if (node == null)
            return;

        inorder_conv(node.left);

        //find predecessor node
        Node<T> pred = find_pred2(node);
        if (pred != null) {	//predecessor exists
			if (pred.right != null) {
				
				if (pred.rightThread == true) {
					pred.right = null;
					pred.rightThread = false;
				}
			}
		}
        

        inorder_conv(node.right);
    }
    
    //from AVLTRee
  
    
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //Helper functions
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        public boolean find_node(T data) {
        	boolean found = false;
        	Node<T> cur = root; 
        	if (cur == null) {
    			return found;
    		}
        	while (!found) {
    			if(cur.data.equals(data)) {	// found node do not insert
    				return true;
    			} else if (cur.data.compareTo(data) > 0) {
    				
    				//check of left child is not null
    				if (cur.left != null) {
    					//go to left child 
    					cur = cur.left;
    				} else { 	//not found can be inserted 
    					return found;
    				}
    			} else if (cur.data.compareTo(data) < 0) {
    				
    				//check of right child is not null
    				if (cur.right != null) {
    					//go to right child 
    					if (cur.rightThread != true) {
    						cur = cur.right;
						} else {
							return found;
						}
    				} else { 	//not found can be inserted 
    					return found;
    				}
    			}
    		}//while
        	
        	return found;
        }
        
        public Node<T> find_node2(T data) {
        	boolean found = false;
        	Node<T> cur = root; 
        	if (cur == null) {
    			return cur;
    		}
        	while (!found) {
    			if(cur.data.equals(data)) {	// found node do not insert
    				return cur;
    			} else if (cur.data.compareTo(data) > 0) {
    				
    				//check of left child is not null
    				if (cur.left != null) {
    					//go to left child 
    					cur = cur.left;
    				} else { 	//not found can be inserted 
    					return null;
    				}
    			} else if (cur.data.compareTo(data) < 0) {
    				
    				//check of right child is not null
    				if (cur.right != null) {
    					//go to right child 
    					if (cur.rightThread != true) {
    						cur = cur.right;
						} else {
							return null;
						}
    				} else { 	//not found can be inserted 
    					return null;
    				}
    			}
    		}//while
        	
        	return cur;
        }
        
        public Node<T> find_pred(Node<T> node) {
        	boolean found = false;
        	Node<T> cur = root; 
        	Node<T> pred = null; 
        	if (cur == null) {
    			return cur;
    		}
        	
        	if (node.left != null) {	//predecessor exists
        		cur = node.left;
    		} else {	//predecessor does not exists
    			return pred;
    		}
        	
        	//find right-most node
        	
        	while (!found) {
    			if(cur.right == null) {	// found right-most node predecessor
    				return cur;
    			} else {
    				//go to right child 
    				
    				if (!cur.rightThread) {
    					cur = cur.right;
					}else {
						return cur;
					}
    			}//else
    		}//while
        	
        	return cur;
        }
        
        public Node<T> ins(Node<T> node) {
        	boolean inserted = false;
        	Node<T> cur = root; 
        	while (!inserted) {
        		
        		if(cur == null) {	// root is null
    				root = node;
    				node.height = 0;
        			return node;
    			} else if (cur.data.compareTo(node.data) > 0) {
    				
    				//check of left child is not null
    				if (cur.left != null) {
    					//go to left child 
    					cur = cur.left;
    				} else { 	//not found can be inserted 
    					//System.out.println("inserted " + node.data + " to the left of " + cur.data);
    					//insert the node
    					cur.left = node; 
    					//update height
    					node.height = 0;
    					inserted = true;
    				}
    			} else if (cur.data.compareTo(node.data) < 0) {
    				
    				//check of right child is not null
    				if (cur.right != null) {
    					//go to right child 
    					if (cur.rightThread != true) {
    						cur = cur.right;
						}else {
							cur.right = node; 
	    					//update height
	    					node.height = 0;
	    					inserted = true;
						}
    				} else { 	//not found can be inserted 
    					//System.out.println("inserted " + node.data + " to the right of " + cur.data);
    					//insert the node
    					cur.right = node; 
    					//update height
    					node.height = 0;
    					inserted = true;
    				}
    			}
        		//System.out.println("loop");
    		}//while
        	return node;
        }
        
        public Node<T> update_h(Node<T> node) {
        	
        	boolean updated = false;
        	Node<T> nNode = node;
        	Node<T> prev = null;
        	Node<T> cur = root; 
        	Node<T> unbalanced_node = null;
        	int balance_factor = 0;
        	
        	while (!updated) {
        		//System.out.println("updating " + cur.data + " with " + node.data);
        		if(cur.data.equals(node.data) ) {	// found current node
    				//check if prev is not null
        			//System.out.println(cur.data + " == " + node.data);
        			up_h(cur);
        			if (prev == null) {	//it is root we can return
        				balance_factor = bal_fac(cur);
        				if ((balance_factor < -1) || (balance_factor > 1)) { 	//unbalanced node 
        					if (unbalanced_node == null) {
        						//System.out.println("unbalanced node is " + cur.data);
        						unbalanced_node = cur;
        						//System.out.println("balance without prev");
        						node = bal(unbalanced_node, nNode);
        						cur = root;
    						}
    					} else {
    						//System.out.println("balance factor of node " + cur.data + " is " + balance_factor);
    					}
        				
        				updated = true;
    					
    				} else {
    					 
    					up_h(cur);
    					balance_factor = bal_fac(cur);
    	    			if ((balance_factor < -1) || (balance_factor > 1)) { 	//unbalanced node 
    	    				if (unbalanced_node == null) {
    	    					//System.out.println("unbalanced node is " + prev.data);
    	    					unbalanced_node = cur;
    	    					//System.out.println("balance with prev");
    	    						
    	    					node = bal(unbalanced_node, nNode);
    	    					cur = root;
    	    					//System.out.println("top node is " + node.data);
    						}
    						
    					} else {	//prev node is updated 
    					
    						node = prev;
    						//System.out.println("prev: " + prev.data);
    						//start again
    						prev = null;
    						cur = root;
    						//System.out.println("cur: " + cur.data);
    					}//else
    				}//else
        			
    			} else if (cur.data.compareTo(node.data) > 0) { 	//go left to node
    				//System.out.println(cur.data + " > " + node.data);
    				//check of left child is not null
    				if (cur.left != null) {
    					//go to left child 
    					//System.out.println("go left");
    					prev = cur;
    					cur = cur.left;
    				} else { 	//not found can be inserted 
    					//System.err.println("node to update not found");;
    					updated = true;
    				}
    			} else if (cur.data.compareTo(node.data) < 0) {		//go right to node
    				//System.out.println(cur.data + " < " + node.data);
    				//System.out.println(cur.right.data);
    				//check of right child is not null
    				if (cur.right != null) {
    					//go to right child 
    					//System.out.println("go right");
    					
    					
    					if (cur.rightThread != true) {
    						prev = cur;
    						cur = cur.right;
						}else {
							updated = true;
						}
    				} else { 	//not found can be inserted 
    					//System.err.println("node to update not found");;
    					updated = true;
    				}
    			}
    		}//while
        	return unbalanced_node;
        }

        public Node<T> update_d(Node<T> node) {
        	//Havent started with yet
        	boolean updated = false;
        	Node<T> prev = null;
        	Node<T> cur = root; 
        	Node<T> unbalanced_node = null;
        	int balance_factor = 0;
        	if (node == null) {
    			return null;
    		}
        	
        	while (!updated) {
        		//System.out.println("updating " + cur.data + " with " + node.data);
        		if(cur.data.equals(node.data) ) {	// found current node
    				//check if prev is not null
        			//System.out.println(cur.data + " == " + node.data);
        			if (prev == null) {	//it is root we can return
        				//update node height by comparing each node
        				up_h(cur);
        				
        				balance_factor = bal_fac(cur);
        				if ((balance_factor < -1) || (balance_factor > 1)) { 	//unbalanced node 
        					if (unbalanced_node == null) {
        						//System.out.println("unbalanced node is " + cur.data);
        						unbalanced_node = cur;
        						node = bal2(unbalanced_node);
        						cur = root;
    						}
    					} else {
    						//System.out.println("balance factor of node " + cur.data + " is " + balance_factor);
    					}
        				
        				updated = true;
    					
    				} else {
    					
    					//update node height by comparing each node
        				up_h(cur);
        				
        				balance_factor = bal_fac(cur);
        				if ((balance_factor < -1) || (balance_factor > 1)) { 	//unbalanced node 
        					if (unbalanced_node == null) {
        						//System.out.println("unbalanced node is " + cur.data);
        						unbalanced_node = cur;
        						node = bal2(unbalanced_node);
        						
    						}
    					} else {
    						//System.out.println("balance factor of node " + cur.data + " is " + balance_factor);
    					}
        				
    					//start again
        				node = prev;
    					prev = null;
    					cur = root;
    				}
        			
    			} else if (cur.data.compareTo(node.data) > 0) { 	//go left to node
    				//System.out.println(cur.data + " > " + node.data);
    				//check of left child is not null
    				if (cur.left != null) {
    					//go to left child 
    					//System.out.println("go left");
    					prev = cur;
    					cur = cur.left;
    				} else { 	//not found can be inserted 
    					//System.err.println("node to update not found");;
    					updated = true;
    				}
    			} else if (cur.data.compareTo(node.data) < 0) {		//go right to node
    				//System.out.println(cur.data + " < " + node.data);
    				//System.out.println(cur.right.data);
    				//check of right child is not null
    				if (cur.right != null) {
    					//go to right child 
    					//System.out.println("go right");
    					prev = cur;
    					if (cur.rightThread != true) {
    						cur = cur.right;
						}else {
							updated = true;
						}
    				} else { 	//not found can be inserted 
    					//System.err.println("node to update not found");;
    					updated = true;
    				}
    			}
    		}//while
        	return unbalanced_node;
        }
        
        public int bal_fac(Node<T> node) {
        	int balance_factor = 0;
        	int left = 0;
        	int right = 0;
        	//left
        	if (node.left != null) {
    			left = node.left.height;
    		} else {
    			left = -1;
    		}
        	
        	//right
        	if (node.right != null) {
    			if (node.rightThread != true) {
					right = node.right.height;
				} else {
					right = -1;
				}
    		} else {
    			right = -1;
    		}
        	
        	//calc
        	balance_factor = right - left;
        	
        	return balance_factor;
        }
        
        public void up_h(Node<T> node) {
        	//System.out.println("uo_h");
        	int left = 0;
        	int right = 0;
        	//left
        	if (node.left != null) {
    			left = node.left.height;
    		} else {
    			left = 0;
    		}
        	
        	//right
        	if (node.right != null) {
    			right = node.right.height;
    			if (node.rightThread != true) {
					right = node.right.height;
				}else {
					right = 0;
				}
    		} else {
    			right = 0;
    		}
        	
        	//calc height
        	if (left > right) {
    			node.height = left+1;
    			//System.out.println("height of " + node.data + ": " + node.height);
    		} else if (left < right){
    			node.height = right+1;
    			//System.out.println("height of " + node.data + ": " + node.height);
    		} else {	//left == right
    			if ((node.rightThread == true) && (node.left == null)) {
    				node.height = 0;
    			} else {
    				node.height = right+1;	//left or right doesnt matter
    			}
    			
    			//System.out.println("height of " + node.data + ": " + node.height);
    		}
        } 
        
        public Node<T> find_P(Node<T> node) {	//find parent of node
        	boolean found = false;
        	Node<T> cur = root; 
        	Node<T> parent = null; 
        	if (cur == null) {
    			return null;
    		}
        	//System.out.println("finding parent of " + node.data);
        	//System.out.println("starting at root " + cur.data);
        	while (!found) {
    			if(cur.data.equals(node.data)) {	// found node, no parent
    				return null;
    			} else if (cur.data.compareTo(node.data) > 0) {
    				
    				//check of left child is not null
    				if (cur.left != null) {
    					if (cur.left.data.equals(node.data)) {		//cur is the parent
    						//System.out.println("found parent is " + cur.data);
    						parent = cur;
    						found = true;
    					} else {
    						//go to left child 
    						cur = cur.left;
    					}
    				} else { 	//not found can be inserted 
    					return parent;
    				}
    			} else if (cur.data.compareTo(node.data) < 0) {
    				
    				//check of right child is not null
    				if (cur.right != null) {
    					if (cur.right.data.equals(node.data)) {		//cur is the parent
    						parent = cur;
    						found = true;
    					} else {
    						//go to right child 
    						if (cur.rightThread != true) {
        						cur = cur.right;
    						} else {
								return parent;
							}
    					}
    				} else { 	//not found can be inserted 
    					return parent;
    				}
    			}
    		}//while
        	
        	return parent;
        }
        
       
        public Node<T> rot_L(Node<T> unbalanced_node) {
        	
        	//find parent
        	Node<T> parent = find_P(unbalanced_node);
        	Node<T> child = unbalanced_node.right;
        	boolean rotated = false;
        	//System.out.println("left rotation about " + unbalanced_node.data);
        	
        	while (!rotated) {
    			//case 1 : no parent, unbalanced node is the root
        		if (parent != null) {	//there is a parent
    				if (parent.right == unbalanced_node) {
    					parent.right = child;
    					//System.out.println(parent.data + " right child becomes " + child.data);
    				} else {
    					parent.left = child;
    					//System.out.println(parent.data + " left child becomes " + child.data);
    				}
    			} else {		//unbalanced node is the root
    				root = child;
    				//System.out.println("root is now " + root.data);
    			}
        		
        		//CHECK if child has a left child
        		if (child.left != null) {	//left child exist
        			unbalanced_node.right = child.left; 
    				//System.out.println(unbalanced_node.data + " right child becomes " + child.left.data);
    				child.left = unbalanced_node;
    				//System.out.println(child.data + " left child becomes " + unbalanced_node.data);
    			} else {
    				unbalanced_node.right = child; 
    				unbalanced_node.rightThread = true;
    				
    				//System.out.println(unbalanced_node.data + " right child becomes null");
    				child.left = unbalanced_node;
    				//System.out.println(child.data + " left child becomes " + unbalanced_node.data);
    			}
        		
        		//update the height
        		//unbalanced_node.height =  child.height-1;
        		up_h(unbalanced_node);
        		up_h(child);
        		rotated = true; 
    		}
        	
        	return child;
        }
        
        public Node<T> rot_R(Node<T> unbalanced_node) {
        	
        	//find parent
        	Node<T> parent = find_P(unbalanced_node);
        	Node<T> child = unbalanced_node.left;
        	boolean rotated = false;
        	//System.out.println("right rotation about " + unbalanced_node.data);
        	while (!rotated) {
    			//case 1 : no parent, unbalanced node is the root
        		if (parent != null) {	//there is a parent
    				if (parent.left == unbalanced_node) {
    					parent.left = child;
    					//System.out.println(parent.data + " left child becomes " + child.data);
    				} else {
    					parent.right = child;
    					//System.out.println(parent.data + " right child becomes " + child.data);
    				}
    			} else {		//unbalanced node is the root
    				root = child;
    				//System.out.println("root is now " + root.data);
    			}
        		
        		//CHECK if child has a right child
        		if (child.right != null) {	//right child exist
        			
        			if (child.rightThread != true) {
        				unbalanced_node.left = child.right; 
					}else {
						unbalanced_node.left = null;
					}
    				//System.out.println(unbalanced_node.data + " left child becomes " + child.right.data);
    				child.right = unbalanced_node;
    				//System.out.println(child.data + " right child becomes " + unbalanced_node.data);
    			} else {
    				unbalanced_node.left = null; 
    				//System.out.println(unbalanced_node.data + " left child becomes null");
    				child.right = unbalanced_node;
    				//System.out.println(child.data + " right child becomes " + unbalanced_node.data);
    			}
        		
        		//update the height
        		//unbalanced_node.height =  child.height-1;
        		up_h(unbalanced_node);
        		up_h(child);
        		rotated = true;
    		}
        	
        	return child;
        }
        
        public Node<T> bal(Node<T> unbalanced_node, Node<T> nNode) {
        	
        	//find cases 
        	//System.out.println("New node: " + nNode.data);
        	//System.out.println("unbalanced node: " + unbalanced_node.data);
        	Node<T> temp = null;
        	
        	if (bal_fac(unbalanced_node) < -1) {
        		if (unbalanced_node.left.data.compareTo(nNode.data) > 0) {		// case 1
        			//System.out.println("Case 1");
        			//parent = find_P(unbalanced_node);
        			
        			//temp = rotation(parent, unbalanced_node, unbalanced_node.left, null);
        			temp = rot_R(unbalanced_node);
        			
        		} else if (unbalanced_node.left.data.compareTo(nNode.data) < 0) {	// Case 2	
        			//System.out.println("Case 2");
        			//parent = find_P(unbalanced_node);
        			
        			
        			//temp = rotation(parent, unbalanced_node, unbalanced_node.left, unbalanced_node.left.right);
        			temp = rot_L(unbalanced_node.left);
        			temp = rot_R(unbalanced_node);
        		}
    		} else if (bal_fac(unbalanced_node) > 1) {
    			if (unbalanced_node.right.data.compareTo(nNode.data) < 0) {	//Case 4
        			//System.out.println("Case 4");
        			//parent = find_P(unbalanced_node);
        			
        			//temp = rotation(parent, unbalanced_node, unbalanced_node.right, null);
        			temp = rot_L(unbalanced_node);
        			
        		} else if (unbalanced_node.right.data.compareTo(nNode.data) > 0) {	//Case 3
        			//System.out.println("Case 3");
        			
        			//parent = find_P(unbalanced_node);
        			
        			
        			//temp = rotation(parent, unbalanced_node, unbalanced_node.right, unbalanced_node.right.left);
        			temp = rot_R(unbalanced_node.right);
        			temp = rot_L(unbalanced_node);
        		}
    		}	
        	
        	return temp;
        }
        
        public Node<T> bal2(Node<T> unbalanced_node) {
        	//find cases 
        	//System.out.println("New node: " + nNode.data);
        	//System.out.println("unbalanced node: " + unbalanced_node.data);
        	Node<T> temp = null;
        	boolean balanced = false;
        	while (!balanced) {
        		if (unbalanced_node.right != null) {
            		if ((bal_fac(unbalanced_node) > 1) && (bal_fac(unbalanced_node.right) == 1)) {
            			//System.out.println("Case1");
            			//one left rotation about unbalanced_node
            			//System.out.println("one left rotation about unbalanced_node");
            			temp = rot_L(unbalanced_node);
            			balanced = true;
            		}else if ((bal_fac(unbalanced_node) > 1) && (bal_fac(unbalanced_node.right) == 0)) {
            			//System.out.println("Case2");
            			//one left rotation about unbalanced_node
            			//System.out.println("one left rotation about unbalanced_node");
            			temp = rot_L(unbalanced_node);
            			balanced = true;
            		}else if (unbalanced_node.right.left != null) {
            			if ((bal_fac(unbalanced_node) > 1) && (bal_fac(unbalanced_node.right) == -1) && (bal_fac(unbalanced_node.right.left) == -1)) {
                			//System.out.println("Case3");
                			//right rotation about unbalanced_node.right
                			//System.out.println("right rotation about unbalanced_node.right");
                			temp = rot_R(unbalanced_node.right);
                			//left  rotation about unbalanced_node
                			//System.out.println("left  rotation about unbalanced_node");
                			temp = rot_L(unbalanced_node);
                			
                			balanced = true;
            			}else if ((bal_fac(unbalanced_node) > 1) && (bal_fac(unbalanced_node.right) == -1) && (bal_fac(unbalanced_node.right.left) == 1)) {
                			//System.out.println("Case4");
                			//right rotation about unbalanced_node.right
                			//System.out.println("right rotation about unbalanced_node.right");
                			temp = rot_R(unbalanced_node.right);
                			//left  rotation about unbalanced_node
                			//System.out.println("left  rotation about unbalanced_node");
                			temp = rot_L(unbalanced_node);
                			balanced = true;
            			}else if ((bal_fac(unbalanced_node) > 1) && (bal_fac(unbalanced_node.right) == -1) && (bal_fac(unbalanced_node.right.left) == 0)) {
                			//System.out.println("Case5");
                			//right rotation about unbalanced_node.right
                			//System.out.println("right rotation about unbalanced_node.right");
                			temp = rot_R(unbalanced_node.right);
                			//left  rotation about unbalanced_node
                			//System.out.println("left  rotation about unbalanced_node");
                			temp = rot_L(unbalanced_node);
                			balanced = true;
            			}
        			}
            		
        		}
            	if (unbalanced_node.left != null) {
            		
        			if ((bal_fac(unbalanced_node) < -1) && (bal_fac(unbalanced_node.left) == -1)) {
            			//System.out.println("Case1");
            			//one right rotation unbalanced_node
            			//System.out.println("one right rotation unbalanced_node");
            			temp = rot_R(unbalanced_node);
            			balanced = true;
            		}else if ((bal_fac(unbalanced_node) < -1) && (bal_fac(unbalanced_node.left) == 0)) {
            			//System.out.println("Case2");
            			//one right rotation unbalanced_node
            			//System.out.println("one right rotation unbalanced_node");
            			temp = rot_R(unbalanced_node);
            			balanced = true;
        			}else if (unbalanced_node.left.right != null) {
            			if ((bal_fac(unbalanced_node) < -1) && (bal_fac(unbalanced_node.left) == 1) && (bal_fac(unbalanced_node.left.right) == 1)) {
                			//System.out.println("Case3");
                			//left rotation about unbalanced_node.left
                			//System.out.println("left rotation about unbalanced_node.left");
                			temp = rot_L(unbalanced_node.left);
                			//right  rotation about unbalanced_node
                			//System.out.println("right  rotation about unbalanced_node");
                			temp = rot_R(unbalanced_node);
                			balanced = true;
            			}else if ((bal_fac(unbalanced_node) < -1) && (bal_fac(unbalanced_node.left) == 1) && (bal_fac(unbalanced_node.left.right) == -1)) {
                			//System.out.println("Case4");
                			//left rotation about unbalanced_node.left
                			//System.out.println("left rotation about unbalanced_node.left");
                			temp = rot_L(unbalanced_node.left);
                			//right  rotation about unbalanced_node
                			//System.out.println("right  rotation about unbalanced_node");
                			temp = rot_R(unbalanced_node);
                			balanced = true;
            			} else if ((bal_fac(unbalanced_node) < -1) && (bal_fac(unbalanced_node.left) == 1) && (bal_fac(unbalanced_node.left.right) == 0)) {
                			//System.out.println("Case5");
                			//left rotation about unbalanced_node.left
                			//System.out.println("left rotation about unbalanced_node.left");
                			temp = rot_L(unbalanced_node.left);
                			//right  rotation about unbalanced_node
                			//System.out.println("right  rotation about unbalanced_node");
                			temp = rot_R(unbalanced_node);
                			balanced = true;
            			}
        			}
        		}//if (unbalanced_node.left != null)
            	//System.out.println("infinite loop missed a case");
            
    		}//while
        	
        	
        	
        	
        	//temporary
        	return temp;
        			
        }
        
        public Node<T> del_c(T data) {
        	Node<T> dNode = null;
        	Node<T> temp = null;
        	Node<T> parent = null;
        	Node<T> parent2 = null; 
        	
        	//find node to be deleted
        	if (find_node(data)) {
    			dNode = find_node2(data);
    			//System.out.println("found node to be deleted: " + dNode.data);
    		} else {	//node not found, no deletion
    			//System.out.println("did not find node to be deleted: " + data);
    			return null;
    		}
        	
        	//find pedecessor of deleted node.
    		Node<T> pred = find_pred(dNode);
    		//System.out.println("pedecessor is " + pred);
        	//Disconnect predecessor
    		if (pred == null) {		//predecessor
    			//right child becomes new root
    			
    			
    			//check if theres a parent
    			if (root == dNode) {	//dNode is root 
    				//System.out.println("dNode is root");
    				//check for right child
    				if (dNode.right != null) {	//right child exist
    					temp = dNode.right;
    					dNode.right = null;
    					root = temp;
    					root.height = 1;
    					return root;
    				} else {
    					//System.out.println("deleting root");
    					this.root = null;
    				}//else of if (dNode.right != null)
    			} else {	//parent exists
    				//System.out.println("dNode is not root");
    				//find parent
    				parent = find_P(dNode);
    				//System.out.println("parent is " + parent);
    				//check for right child
    				if (dNode.right != null) {	//right child exist
    					if (parent.right == dNode) {
    						//System.out.println(parent.data + " right child becomes " + dNode.right.data);
    						//System.out.println(dNode.data + " right child becomes null");
    						
    						if (dNode.rightThread) {
								parent.right = dNode.right;
								parent.rightThread = true;
								
								dNode.rightThread = false;
								parent2 = parent;
							}else {
	    						//first node that needs to be balanced
	    						parent2 = parent.right;
							}
    						
    						dNode.right = null;
    					} else {	
    						//System.out.println(parent.data + " left child becomes " + dNode.right.data);
    						//System.out.println(dNode.data + " left child becomes null");
    						
    						if (dNode.rightThread) {
								parent.right = null;
								
								dNode.rightThread = false;
								parent2 = parent;
							}else {
	    						//first node that needs to be balanced
								parent.left = dNode.right;
								//first node that needs to be balanced
	    						parent2 = parent.left;
							}
    						dNode.right = null;
    						
    						
    					}
    				} else {
    					if (parent.right == dNode) {
    						parent.right = null;
    						//System.out.println(parent.data + " right child becomes null");
    						parent2 = parent;
    					} else {	
    						parent.left = null;
    						//System.out.println(parent.data + " left child becomes null");
    						parent2 = parent;
    					}
    				}//else of if (dNode.right != null)
    				
    				//update balance
    			}//else of if (root == dNode) 
    			return parent2;
    		} else {	//pred exists
    			//disconnect predecessor
    			//find parent
    			parent2 = find_P(pred);
    			if (parent2 == dNode) {
    				parent2 = pred;
    			} 
    			parent = find_P(pred);
    			
    			//check if predecessor does not have left child
    			if (pred.left == null) {	//no left child
    				if (parent.right == pred) {
    					
    					if (pred.rightThread) {
							parent.right = pred.right;
							parent.rightThread = true;
							pred.rightThread = false;
						}else {
							parent.right = null;
						}
    					//System.out.println(parent.data + " right child becomes null");
    				} else {
    					parent.left = null;
    					//System.out.println(parent.data + " left child becomes null");
    				}//else of if (parent.right == pred)
    			} else {
    				if (parent.right == pred) {
    					parent.right = pred.left;
    					//System.out.println(parent.data + " right child becomes " + pred.left.data);
    					
    					
    					if (pred.rightThread) {
    						Node<T> t = pred.left.right;
    						while (t.right != null) {
								t = t.right;
							}
    						
							t = pred.right;
							t.rightThread = true;
							pred.rightThread = false;
							
						}else {
							parent.right = null;
						}
    					pred.left = null;
    					
    				} else {
    					parent.left = pred.left;
    					//System.out.println(parent.data + " left child becomes " + pred.left.data);
    					if (pred.rightThread) {
    						Node<T> t = pred.left.right;
    						while (t.right != null) {
								t = t.right;
							}
    						
							t = pred.right;
							t.rightThread = true;
							pred.rightThread = false;
							
						}else {
							parent.right = null;
						}
    					pred.left = null;
    				}//else of if (parent.right == pred)
    			}//else of if (pred.left == null) 
    		}
    		
    		//predecessor replaces dNode
    		if (find_P(dNode) == null) {	//delete the root
    			Node<T> new_pred = find_pred(dNode);
    			if (dNode.left != null) {
    				pred.left = dNode.left; 
    				//System.out.println(pred.data + " left child becomes " + dNode.left.data);
    			}
    			if (dNode.right != null) {
    				pred.right = dNode.right; 
    				//System.out.println(pred.data + " right child becomes " + dNode.right.data);
    			}
    			root = pred;
    			if (new_pred.rightThread) {
					new_pred.right = pred;
				}
    			//System.out.println(pred.data + " becomes root");
    		} else {
    			Node<T> new_pred = find_pred(dNode);
    			parent = find_P(dNode);
    			//System.out.println("parent of dNode: " + parent.data);
    			if (parent.right == dNode) {
    				parent.right = pred;
    				//System.out.println(parent.data + " right child becomes " + pred.data);
    				
    			} else {
    				parent.left = pred;
    				//System.out.println(parent.data + " left child becomes " + pred.data);
    			}
    			
    			if (dNode.left != null) {
    				if (dNode.left != pred) {
    					//System.out.println(pred.data + " left child becomes " + dNode.left.data);
    					//System.out.println(dNode.data + " left child becomes null");
    					pred.left = dNode.left;
    					dNode.left = null;
    					
    				} 
    			}
    			if (dNode.right != null) {
    				//System.out.println(pred.data + " right child becomes " + dNode.right.data);
    				//System.out.println(dNode.data + " right child becomes null");
    				pred.right = dNode.right;
    				dNode.right = null;
    			}
    			
    			if (new_pred.rightThread) {
					new_pred.right = pred;
				}
    		}
        	return parent2;
        }
        
    

}
