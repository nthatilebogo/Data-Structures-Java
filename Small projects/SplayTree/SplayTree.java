/**
 * Name: Onthatile Lebogo
 * Student Number: u20589507
 */

public class SplayTree<T extends Comparable<T>> {

	protected enum SplayType {
		SPLAY,
		SEMISPLAY,
		NONE
	}	

	protected Node<T> root = null;
	
	/**
	 * Prints out all the elements in the tree
	 * @param verbose
	 *			If false, the method simply prints out the element of each node in the tree
	 *			If true, then the output provides additional detail about each of the nodes.
	 */
	public void printTree(boolean verbose) {
		String result;
		result = preorder(root, verbose);
		System.out.println(result);
	}
	
	protected String preorder(Node<T> node, boolean verbose) {
		if (node != null) {
			String result = "";
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.elem.toString() + " ";
			}
			result += preorder(node.left, verbose);
			result += preorder(node.right, verbose);
			return result;
		}
		return "";
	}
	
	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	/**
	* Inserts the given element into the tree, but only if it is not already in the tree.
	* @param elem 
	* 		 	The element to be inserted into the tree
	* @return 
	*			Returns true if the element was successfully inserted into the tree. 
	*			Returns false if elem is already in the tree and no insertion took place.
	*
	*/
	public boolean insert(T elem) {

		//Your code goes here
		if (root == null) {
			root = new Node<T>(elem);
			return true;
		}
		if (contains(elem)) {
			return false;
		}
		return ins(root,elem);
	}
	
	/**
	* Checks whether a given element is already in the tree.
	* @param elem 
	* 		 	The element being searched for in the tree
	* @return 
	*			Returns true if the element is already in the tree
	*			Returns false if elem is not in the tree
	*
	*/
	public boolean contains(T elem) {

		//Your code goes here
		return srch(root, elem);
	}
	
	/**
	 * Accesses the node containing elem. 
	 * If no such node exists, the node should be inserted into the tree.
	 * If the element is already in the tree, the tree should either be semi-splayed so that 
	 * the accessed node moves up and the parent of that node becomes the new root or be splayed 
	 * so that the accessed node becomes the new root.
	 * @param elem
	 *			The element being accessed
	 * @param type
	 *			The adjustment type (splay or semi-splay or none)
	 */
	public void access(T elem, SplayType type) {

		//Your code goes here
		if (root.elem.equals(elem)) {
			return;
		}
		if (this.contains(elem)) {
			if (type.equals(SplayType.SPLAY)) {
				splay(srch_Node(root, elem));
			} else if (type.equals(SplayType.SEMISPLAY)) {
				semisplay(srch_Node(root, elem));
			}
		} else {
			this.insert(elem);
		}
	}
	
	/**
	 * Semi-splays the tree using the parent-to-root strategy
	 * @param node
	 *			The node the parent of which will be the new root
	 */
	protected void semisplay(Node<T> node) {
		
		//Your code goes here
		while (node != root) {
			int i = Case(root,node,null,null); //Figure which case is needs to be implemented.
			if (i == 1) {
				//System.err.println("case 1");
				s_Case1(root,node,null,null,i);
			} else if (i == 2) {
				//System.err.println("case 2");
				ss_Case2(root,node,null,null,4);
				Node<T> p = find(root, node, null, null, null, null, "p");
				node = p;
				//System.out.println("p="+ p + "  r="+ root + "  n=" + node);
				
				if (p == root) {
					return;
				}
			} else if (i == 3) {
				//System.err.println("case 3");
				s_Case3(root,node,null,null,i);	
			}
		}
	}

	/**
	 * Splays the tree using the node-to-root strategy
	 * @param node
	 *			The node which will be the new root
	 */
	protected void splay(Node<T> node) {
		
		//Your code goes here
		while (node != root) {
			int i = Case(root,node,null,null); //Figure which case is needs to be implemented.
			if (i == 1) {
				//System.err.println("case 1");
				s_Case1(root,node,null,null,i);
			} else if (i == 2) {
				//System.err.println("case 2");
				s_Case2(root,node,null,null,i);
			} else if (i == 3) {
				//System.err.println("case 3");
				s_Case3(root,node,null,null,i);	
			}
		}
}
	//Helper functions
	public boolean ins(Node<T> node, T newEl) {
		if (node.elem.compareTo(newEl) < 0) {
			if (node.right != null) {
				return ins(node.right, newEl);
				 
			} else {
				node.right = new Node<T>(newEl);
				return true;
			}
		} else {
			if (node.left != null) {
				return ins(node.left, newEl);
				
			} else {
				node.left = new Node<T>(newEl);
				return true;
			}
		}
	}
	
	public boolean srch(Node<T> node, T newEl) {
		if (node.elem.equals(newEl)) {
			return true;
		}
		if (node.elem.compareTo(newEl) < 0) {
			if (node.right == null) {
				return false;
			} else {
				return srch(node.right, newEl);
			}
		} else {
			if (node.left == null) {
				return false;
			} else {
				return srch(node.left, newEl);
			}
		}
	}

	public Node<T> srch_Node(Node<T> node, T newEl) {
		if (node.elem.equals(newEl)) {
			return node;
		}
		if (node.elem.compareTo(newEl) < 0) {
			if (node.right == null) {
				return null;
			} else {
				return srch_Node(node.right, newEl);
			}
		} else {
			if (node.left == null) {
				return null;
			} else {
				return srch_Node(node.left, newEl);
			}
		}
	}
	
	public int Case(Node<T> cur, Node<T> a, Node<T> p, Node<T> g) {
		while (cur != a) {
			if (cur.elem.compareTo(a.elem) < 0) {
				if (cur.right == null) {
					return 0;
				} else {
					if (p == null) {
						p = cur;
					}else {
						g = p;
						p = cur;
					}
					cur = cur.right;
				}
			} else if (cur.elem.compareTo(a.elem) > 0) {
				if (cur.left == null) {
					return 0;
				} else {
					if (p == null) {
						p = cur;
					}else {
						g = p;
						p = cur;
					}
				}
				cur = cur.left;
			}
		}
		
		if ((g == null) && (p == null)) {
			return 0;
		}
		//1
		if ((g == null) && (p != null)) {
			return 1;
		}
		//2
		if ((g.left == p) && (p.left == a)) {
			return 2;
		} else if ((g.right == p) && (p.right == a)) {
			return 2;
		} 
		//3
		if ((g.left == p) && (p.right == a)) {
			return 3;
		} else if ((g.right == p) && (p.left == a)) {
			return 3;
		} 
		return 0;
	}

	public void s_Case1(Node<T> cur, Node<T> a, Node<T> p, Node<T> g, int i) {
		if (cur.elem.equals(a.elem)) {
			if (p != null) {
				if (p.left != null) {
					if (p.left == a) {
						right(a, p, g, i);
						return;
					}
				}
				if (p.right != null) {
					if (p.right == a) {
						left(a, p, g, i);
						return;
					} 
				}
			}
		}
		if (cur.elem.compareTo(a.elem) < 0) {
			if (cur.right == null) {
				
			} else {
				if (p == null) {
					p = cur;
				}else {
					g = p;
					p = cur;
				}
				s_Case1(cur.right, a, p, g, i);
			}
		} else {
			if (cur.left == null) {
				
			} else {
				if (p == null) {
					p = cur;
				}else {
					g = p;
					p = cur;
				}
				s_Case1(cur.left, a, p, g, i);
			}
		}
	}
	
	public void s_Case2(Node<T> cur, Node<T> a, Node<T> p, Node<T> g, int i) {
		if (cur.elem.equals(a.elem)) {
			if (g != null) {
				if (g.left != null) {
					if (g.left == p) {
						right(a, p, g, i);
						return;
					}	
				}
				if (g.right != null) {
					if (g.right == p) {
						left(a, p, g, i);
						return;
					} 
				}
			}
			return;
		}
		if (cur.elem.compareTo(a.elem) < 0) {
			if (cur.right == null) {
				
			} else {
				if (p == null) {
					p = cur;
				}else {
					g = p;
					p = cur;
				}
				s_Case2(cur.right, a, p, g, i);
			}
		} else {
			if (cur.left == null) {
				
			} else {
				if (p == null) {
					p = cur;
				}else {
					g = p;
					p = cur;
				}
				s_Case2(cur.left, a, p, g, i);
			}
		}
	}
	
	public void s_Case3(Node<T> cur, Node<T> a, Node<T> p, Node<T> g, int i) {
		if (cur.elem.equals(a.elem)) {
			
			if ((g != null)&&(p != null)) {
				if ((g.left != null) && (p.right != null)) {
					if ((g.left == p) && (p.right == a)) {
						if (a != root) {
							left(a, p, g, i);
							return;
						}
					}
				}
				if ((g.right != null) && (p.left != null)) {
					if ((g.right == p) && (p.left == a)) {
						if (a != root) {
							right(a, p, g, i);
							return;
						}
					}
				}
			}				
		}
		
		if (cur.elem.compareTo(a.elem) < 0) {
			if (cur.right == null) {
				
			} else {
				if (p == null) {
					p = cur;
				}else {
					g = p;
					p = cur;
				}
				s_Case3(cur.right, a, p, g, i);
			}
		} else if (cur.elem.compareTo(a.elem) > 0) {
			if (cur.left == null) {
				
			} else {
				if (p == null) {
					p = cur;
				}else {
					g = p;
					p = cur;
				}
				s_Case3(cur.left, a, p, g, i);
			}
		}
	}

	public void ss_Case2(Node<T> cur, Node<T> a, Node<T> p, Node<T> g, int i) {
		if (cur.elem.equals(a.elem)) {
			
			if (g != null) {
				if (g.left != null) {
					if (g.left == p) {
						//System.out.println("p="+p + "  g="+g);
						right(a, p, g, i);
						return;
					}
				}	
				if (g.right != null) {
					if (g.right == p) {
						left(a, p, g, i);	
						return;
					} 
				}
			}
		}
		if (cur.elem.compareTo(a.elem) < 0) {
			if (cur.right == null) {
				
			} else {
				if (p == null) {
					p = cur;
				}else {
					g = p;
					p = cur;
				}
				ss_Case2(cur.right, a, p, g, i);
			}
		} else {
			if (cur.left == null) {
				
			} else {
				if (p == null) {
					p = cur;
				}else {
					g = p;
					p = cur;
				}
				ss_Case2(cur.left, a, p, g, i);
			}
		}
	}
	
	public void right(Node<T> a, Node<T> p, Node<T> g, int i) {
		if (i == 1) {
			root = a;
			if (a.right != null) {
				p.left = a.right;
			} else {
				p.left = null;
			}
			a.right = p;
		} else if(i == 2) {
			if (g == root) {
				if (p.right != null) {
					g.left = p.right;
				} else {
					g.left = null;
				}
				p.right = g;
				root = p;
				if (a.right != null) {
					p.left = a.right;
				} else {
					p.left = null;
				}
				a.right = p;
				root = a;
			} else {
				Node<T> gg = find(root, a, null, null, null, null, "gg");
				if (gg.left == g) {
					if (p.right != null) {
						g.left = p.right;
					} else {
						g.left = null;
					}
					p.right = g;
					gg.left = p;
					if (a.right != null) {
						p.left = a.right;
					} else {
						p.left = null;
					}
					a.right = p;
					gg.left = a;
				} else if (gg.right == g) {
					if (p.right != null) {
						g.left = p.right;
					} else {
						g.left = null;
					}
					p.right = g;
					gg.right = p;
					if (a.right != null) {
						p.left = a.right;
					} else {
						p.left = null;
					}
					a.right = p;
					gg.right= a;
				}
			}
		} else if(i == 3) {
			Node<T> gg = find(root, a, null, null, null, null, "gg");
			if (g != root) {
				if (a.right != null) {
					p.left = a.right;
				} else {
					p.left = null;
				}
				a.right = p;
				g.right = a;
				if (a.left != null) {
					g.right = a.left;
				} else {
					g.right = null;
				}
				a.left = g;
				if (gg.right == g) {
					gg.right = a;
				}else if (gg.left == g) {
					gg.left = a;
				}
			} else if (g == root) {
				if (a.right != null) {
					p.left = a.right;
				} else {
					p.left = null;
				}
				a.right = p;
				g.right = a;
				if (a.left != null) {
					g.right = a.left;
				} else {
					g.right = null;
				}
				a.left = g;
				root = a;
			}
		} else if(i == 4) {
			if (g == root) {
				if (p.right != null) {
					g.left = p.right;
				} else {
					g.left = null;
				}
				p.right = g;
				root = p;
			} else {
				Node<T> gg = find(root, a, null, null, null, null, "gg");
				if (gg.left == g) {
					if (p.right != null) {
						g.left = p.right;
					} else {
						g.left = null;
					}
					p.right = g;
					gg.left = p;
				} else if (gg.right == g) {
					if (p.right != null) {
						g.left = p.right;
					} else {
						g.left = null;
					}
					p.right = g;
					gg.right = p;
				}
			}
		}
	}
	
	public void left(Node<T> a, Node<T> p, Node<T> g, int i) {
		if (i == 1) {
			root = a;
			if (a.left != null) {
				p.right = a.left;
			} else {
				p.right = null;
			}
			a.left = p;
		} else if(i == 2) {
			if (g == root) {
				if (p.left != null) {
					g.right = p.left;
				} else {
					g.right = null;
				}
				p.left = g;
				root = p;
				if (a.left != null) {
					p.right = a.left;
				} else {
					p.right = null;
				}
				a.left = p;
				root = a;
			} else {
				Node<T> gg = find(root, a, null, null, null, null, "gg");
				
				if (gg.left == g) {
					if (p.left != null) {
						g.right = p.left;
					} else {
						g.right = null;
					}
					p.left = g;
					gg.left = p;
					if (a.left != null) {
						p.right = a.left;
					} else {
						p.right = null;
					}
					a.left = p;
					gg.left = a;
				} else if (gg.right == g) {
					if (p.left != null) {
						g.right = p.left;
					} else {
						g.right = null;
					}
					p.left = g;
					gg.right = p;
					if (a.left != null) {
						p.right = a.left;
					} else {
						p.right = null;
					}
					a.left = p;
					gg.right= a;
				}
			}
		} else if(i == 3){
			Node<T> gg = find(root, a, null, null, null, null, "gg");
			if (g != root) {
				if (a.left != null) {
					p.right = a.left;
				} else {
					p.right = null;
				}
				a.left = p;
				g.left = a;
				if (a.right != null) {
					g.left = a.right;
				} else {
					g.left = null;
				}
				a.right = g;
				if (gg.right == g) {
					gg.right = a;
				}else if (gg.left == g) {
					gg.left = a;
				}
		} else if (g == root) {
			if (a.left != null) {
				p.right = a.left;
			} else {
				p.right = null;
			}
			a.left = p;
			g.left = a;
			if (a.right != null) {
				g.left = a.right;
			} else {
				g.left = null;
			}
			a.right = g;
			root = a;
		} 
	
	} else if(i == 4) {
		Node<T> gg = find(root, a, null, null, null, null, "gg");
		if (g == root) {
			if (p.left != null) {
				g.right = p.left;
			} else {
				g.right = null;
			}
			p.left = g;
			root = p;
		} else {
			
			if (gg.left == g) {
				if (p.left != null) {
					g.right = p.left;
				} else {
					g.right = null;
				}
				p.left = g;
				gg.left = p;
			} else if (gg.right == g) {
				if (p.left != null) {
					g.right = p.left;
				} else {
					g.right = null;
				}
				p.left = g;
				gg.right = p;
			}
		}
		}
	}

	public Node<T> find(Node<T> cur, Node<T> a, Node<T> p, Node<T> g, Node<T> gg, Node<T> ggg,String s) {
		while (cur != a) {
			if (cur.elem.compareTo(a.elem) < 0) {
				if (cur.right == null) {
					return null;
				} else {
					if (p == null) {
						p = cur;
					}else if ( g==null){
						g = p;
						p = cur;
					} else if (gg == null){
						gg = g;
						g = p;
						p = cur;
					}
					else {
						ggg = gg;
						gg = g;
						g = p;
						p = cur;
					}
					
					cur = cur.right;
				}
			} else if (cur.elem.compareTo(a.elem) > 0) {
				if (cur.left == null) {
					return null;
				} else {
					if (p == null) {
						p = cur;
					}else if ( g==null){
						g = p;
						p = cur;
					} else if (gg == null){
						gg = g;
						g = p;
						p = cur;
					}
					else {
						ggg = gg;
						gg = g;
						g = p;
						p = cur;
					}
				}
				cur = cur.left;
			}
		}
		if (s.equals("p")) {
			return p;
		}else if (s.equals("g")) {
			return g;
		} else if (s.equals("gg")) {
			return gg;
		} else if (s.equals("ggg")) {
			return ggg;
		}
		return null;
		
	}

}

	