@SuppressWarnings("unchecked")
public class BST<T extends Comparable<? super T>> {

	protected BSTNode<T> root = null;

	public BST() {
	}

	public void clear() {
		root = null;
	}

	// returns a verbose inorder string of the BST
	public String inorder(BSTNode<T> node) {
		boolean verbose = true;
		if (node != null) {
			String result = "";
			result += inorder(node.left);
			if (verbose) {
				result += node.toString() + "\n";
			} else {
				result += node.element.toString() + " ";
			}
			result += inorder(node.right);
			return result;
		}
		return "";
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	public boolean isEmpty() {
		// Your code goes here
		BSTNode<T> node = root;
		if (node == null) {
			return true;
		} else {
			return false;
		}
	}

	public BSTNode<T> clone() {
		// Your code goes here
		if (root != null) {
			BSTNode<T> clone = new BSTNode<T>(root.element);
			preOrder(root, clone);
			return clone;
		}
		else {
			return null;
		}
		
		
	}

	public BSTNode<T> mirror() {
		// Your code goes here
		if (root != null) {
			BSTNode<T> clone = new BSTNode<T>(root.element);
			mir(root, clone);
			return clone;
		}
		else {
			return null;
		}
	}

	public void insert(T element) {
		// Your code goes here
		if (root == null) {
			root = new BSTNode<T>(element);
			return;
		}
		if (search(element) == element) {
			return;
		}
		ins(root,element);
	}

	public boolean deleteByMerge(T element) {
		// Your code goes here
		BSTNode<T> node = srch_prev_Node(root, null, element);
		if (node == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean deleteByCopy(T element) {
		// Your code goes here
		BSTNode<T> node = srch_prev_Node2(root, null, element);
		if (node == null) {
			return false;
		} else {
			return true;
		}
	}

	public T search(T element) {
		// Your code goes here
		if (root != null) {
			if(srch(root,element)) {
				return element;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public T getPredecessor(T element) {
		// Your code goes here
		if (root == null) {
			return null;
		}
		BSTNode<T> node = srch_Node(root, element);
			
		if (node.left != null) {
			BSTNode<T> n = pre(node.left, element);
			return n.element;
		}
		else {
			BSTNode<T> n = srch_pre(root,null, element);
			if (n != null) {
				return n.element;
			}
			return null;
		}
	}

	public T getSuccessor(T element) {
		// Your code goes here
		if (root == null) {
			return null;
		}
		BSTNode<T> node = srch_Node(root, element);
		if (node.right != null) {
			BSTNode<T> n = suc(node.right, element);
			return n.element;
		}
		else {
			BSTNode<T> n = srch_suc(root,null, element);
			if (n != null) {
				return n.element;
			}
			return null;
		}
	}
	public void preOrder(BSTNode<T> node, BSTNode<T> clone) {
		// Your code goes here
		if (node != null) {
			if (node.left != null) {
				clone.left = new BSTNode<T>(node.left.element);
			}
			if (node.right != null) {
				clone.right = new BSTNode<T>(node.right.element);
			}
			if (clone.left != null) {
				preOrder(node.left,clone.left);
			}
			if (clone.right != null) {
				preOrder(node.right,clone.right);
			}
		}
	}
	public void mir(BSTNode<T> node, BSTNode<T> clone) {
		// Your code goes here
		if (node != null) {
			if (node.left != null) {
				clone.right = new BSTNode<T>(node.left.element);
			}
			if (node.right != null) {
				clone.left = new BSTNode<T>(node.right.element);
			}
			if (clone.right != null) {
				mir(node.left,clone.right);
			}
			if (clone.left != null) {
				mir(node.right,clone.left);
			}
			
		}
	}	
	public void ins(BSTNode<T> node, T newEl) {
		if (node.element.compareTo(newEl) < 0) {
			if (node.right != null) {
				ins(node.right, newEl);
			} else {
				node.right = new BSTNode<T>(newEl);
			}
		} else {
			if (node.left != null) {
				ins(node.left, newEl);
			} else {
				node.left = new BSTNode<T>(newEl);
			}
		}
	}
	public boolean srch(BSTNode<T> node, T newEl) {
		if (node.element.equals(newEl)) {
			return true;
		}
		if (node.element.compareTo(newEl) < 0) {
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
	public BSTNode<T> srch_Node(BSTNode<T> node, T newEl) {
		if (node.element.equals(newEl)) {
			return node;
		}
		if (node.element.compareTo(newEl) < 0) {
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
	public BSTNode<T> pre(BSTNode<T> node, T newEl) {
		
		if (node.right != null) {
			return pre(node.right, newEl);
	    }
		else
		{
			return node;
		}
	}
	public BSTNode<T> srch_pre(BSTNode<T> node,BSTNode<T> pre, T newEl) {
		if (node.element.equals(newEl)) {
			if (pre != null) {
				return pre;
			} else {
				return null;
			}
		}
		if (node.element.compareTo(newEl) < 0) {
			if (node.right != null){
				pre = node;
				return srch_pre(node.right,pre, newEl);
			}
			else {
				return null;
			}
		} else {
			return srch_pre(node.left, pre, newEl);
		}
	}
	public BSTNode<T> suc(BSTNode<T> node, T newEl) {
		if (node.left != null) {
			return suc(node.left, newEl);
	    }
		else
		{
			return node;
		}
	}

	public BSTNode<T> srch_suc(BSTNode<T> node,BSTNode<T> suc, T newEl) {
		if (node.element.equals(newEl)) {
			if (suc != null) {
				return suc;
			} else {
				return null;
			}
		}
		if (node.element.compareTo(newEl) > 0) {
			if (node.left != null){
				
				suc = node;
				return srch_suc(node.left,suc, newEl);
			}
			else {
				return null;
			}
		} else {
			return srch_suc(node.right, suc, newEl);
		}
	}
	public BSTNode<T> srch_prev_Node(BSTNode<T> node, BSTNode<T> prev, T newEl) {
		if (node.element.equals(newEl)) {
			if(prev == null) 
			{
				if ((node.left == null)&&(node.right == null)) {
					root = null;
					return node;
				} else if (node.left == null) {
					root = node.right;
					node.right = null;
				} else if (node.right == null) {
					root = node.left;
					node.left = null;
				} else {
					T el = getPredecessor(newEl);
					BSTNode<T> pre = srch_Node(root, el) ;
					pre.right = node.right;
					root = node.left;
					node.left = null;
					node.right = null;
				}
				return node;
				
			} else {
				if ((node.left == null)&&(node.right == null)) {
					if (prev.left == node) {
						prev.left = null;
					} else {
						prev.right = null;
					}
				} else if (node.left == null) {
					if (prev.left == node) {
						prev.left = node.right;
					} else {
						prev.right = node.right;
					}
				} else {
					T el = getPredecessor(newEl);
					BSTNode<T> pre = srch_Node(root, el) ;
					pre.right = node.right;
					prev.right = node.left;
					node.left = null;
					node.right = null;
				}
				return node;
			}
		
		}
		if (node.element.compareTo(newEl) < 0) {
			if (node.right == null) {
				return null;
			} else {
				prev = node;
				return srch_prev_Node(node.right, prev, newEl);
			}
		} else {
			if (node.left == null) {
				return null;
			} else {
				prev = node;
				return srch_prev_Node(node.left, prev, newEl);
			}
		}
	}
	public BSTNode<T> srch_prev_Node2(BSTNode<T> node, BSTNode<T> prev, T newEl) {
		if (node.element.equals(newEl)) {
			if(prev == null) 
			{
				if ((node.left == null)&&(node.right == null)) {
					root = null;
					return null;
				} else if (node.left == null) {
					root = node.right;
					node.right = null;
				} else if (node.right == null) {
					T p = getPredecessor(newEl);
					BSTNode<T> pre = srch_Node(root, p);
					BSTNode<T> prev2 = srch_prev2(root, null, p);
					if (prev2 == root) {
						root = pre;
						node.left = null;
					} else if (pre.left == null) {
						pre.left = node.left;
						root = pre;
						prev2.right = null;
						node.left = null;
					} else {
						prev2.right = pre.left;
						pre.left = node.left;
						root = pre;
						node.left  = null;
					}
				} else {
					T p = getPredecessor(newEl);
					BSTNode<T> pre = srch_Node(root, p);
					BSTNode<T> prev2 = srch_prev2(root, null, p);
					if (prev2 == root) {
						pre.right = root.right;
						root = pre;
						node.left = null;
						node.right = null;
					} else if (pre.left == null) {
						pre.left = node.left;
						pre.right = node.right;
						root = pre;
						prev2.right = null;
						node.left = null;
						node.right = null;
					} else {
						prev2.right = pre.left;
						pre.left = node.left;
						pre.right = node.right;
						root = pre;
						node.left  = null;
						node.right = null;
					}
				}
				return node;
				
			} else {
				T p = getPredecessor(newEl);
				if (p == null) {
					prev.left = null;
					return node;
				}
				BSTNode<T> pre = srch_Node(root, p);
				BSTNode<T> prev2 = srch_prev2(root, null, p);
				if ((node.left == null)&&(node.right == null)) {
					if (prev.left == node) {
						prev.left = null;
					} else {
						prev.right = null;
					}
				} else if (node.left == null) {
					if (prev.left == node) {
						prev.left = node.right;
					} else {
						prev.right = node.right;
						node.right = null;
					}
				} else if (node.right == null) {
					if (prev2 == node) {
						if (prev.left == node) {
							prev.left = node.left;
							node.left = null;
						}
					} 
					else if (pre.left == null) {
						pre.left = node.left;
						prev.right = pre;
						prev2.right = null;
						node.left = null;
					} else {
						prev2.right = pre.left;
						pre.left = node.left;
						prev.right = pre;
						node.left = null;
					}
				} else {
					
					pre.right = node.right;
					if (prev.right == node) {
						prev.right = node.left;
					} else {
						prev.left = node.left;
					}
					node.left = null;
					node.right = null;
				}
				return node;
			}
		
		}
		if (node.element.compareTo(newEl) < 0) {
			if (node.right == null) {
				return null;
			} else {
				prev = node;
				return srch_prev_Node2(node.right, prev, newEl);
			}
		} else {
			if (node.left == null) {
				return null;
			} else {
				prev = node;
				return srch_prev_Node2(node.left, prev, newEl);
			}
		}
	}
	public BSTNode<T> srch_prev2(BSTNode<T> node, BSTNode<T> prev, T newEl) {
		if (node.element.equals(newEl)) {
			return prev;
		}
		if (node.element.compareTo(newEl) < 0) {
			if (node.right == null) {
				return null;
			} else {
				prev = node;
				return srch_prev2(node.right, prev, newEl);
			}
		} else {
			if (node.left == null) {
				return null;
			} else {
				prev = node;
				return srch_prev2(node.left, prev, newEl);
			}
		}
	}
}
	