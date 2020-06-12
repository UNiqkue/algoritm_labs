package by.nik.lab5;

import java.util.Objects;

public class BinaryTree {
    private BinaryTree parent;
    private BinaryTree left;
    private BinaryTree right;
    private int value;

    public BinaryTree(int val, BinaryTree Parent) {
        this.value = val;
        this.parent = Parent;
    }

    public void add(int val) {
        if (val < this.value) {
            if (this.left == null) {
                this.left = new BinaryTree(val, this);
            } else this.left.add(val);
        } else if (val > this.value) {
            if (this.right == null) {
                this.right = new BinaryTree(val, this);
            } else this.right.add(val);
        }
    }

    private BinaryTree search(BinaryTree tree, int val) {
        if (tree == null) return null;
        switch (Integer.compare(val, tree.value)) {
            case 1:
                return search(tree.right, val);
            case -1:
                return search(tree.left, val);
            case 0:
                return tree;
            default:
                return null;
        }
    }

    public BinaryTree search(int val) {
        return search(this, val);
    }

    public void printInorder(BinaryTree root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);   //рекурсивный вызов левого поддерева
        System.out.println(root.value);
        printInorder(root.right);  //рекурсивный вызов правого поддерева
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "Parent=" + parent +
                ", Left=" + left +
                ", Right=" + right +
                ", Value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree that = (BinaryTree) o;
        return value == that.value &&
                Objects.equals(parent, that.parent) &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, left, right, value);
    }

    public BinaryTree getParent() {
        return parent;
    }

    public void setParent(BinaryTree parent) {
        this.parent = parent;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean remove(int val) {
        //Проверяем, существует ли данный узел
        BinaryTree tree = search(val);
        if (tree == null) {
            //Если узла не существует, вернем false
            return false;
        }
        BinaryTree curTree;
        //Если удаляем корень
        if (tree == this) {
            if (tree.right != null) {
                curTree = tree.right;
            } else curTree = tree.left;

            while (curTree.left != null) {
                curTree = curTree.left;
            }
            int temp = curTree.value;
            this.remove(temp);
            tree.value = temp;

            return true;
        }

        //Удаление листьев
        if (tree.left == null && tree.right == null && tree.parent != null) {
            if (tree == tree.parent.left)
                tree.parent.left = null;
            else {
                tree.parent.right = null;
            }
            return true;
        }
        //Удаление узла, имеющего левое поддерево, но не имеющее правого поддерева
        if (tree.left != null && tree.right == null) {
            //Меняем родителя
            tree.left.parent = tree.parent;
            if (tree == tree.parent.left) {
                tree.parent.left = tree.left;
            } else if (tree == tree.parent.right) {
                tree.parent.right = tree.left;
            }
            return true;
        }
        //Удаление узла, имеющего правое поддерево, но не имеющее левого поддерева
        if (tree.left == null && tree.right != null) {
            //Меняем родителя
            tree.right.parent = tree.parent;
            if (tree == tree.parent.left) {
                tree.parent.left = tree.right;
            } else if (tree == tree.parent.right) {
                tree.parent.right = tree.right;
            }
            return true;
        }
        //Удаляем узел, имеющий поддеревья с обеих сторон
        if (tree.right != null && tree.left != null) {
            curTree = tree.right;
            while (curTree.left != null) {
                curTree = curTree.left;
            }
            //Если самый левый элемент является первым потомком
            if (curTree.parent == tree) {
                curTree.left = tree.left;
                tree.left.parent = curTree;
                curTree.parent = tree.parent;
                if (tree == tree.parent.left) {
                    tree.parent.left = curTree;
                } else if (tree == tree.parent.right) {
                    tree.parent.right = curTree;
                }
                return true;
            }
            //Если самый левый элемент НЕ является первым потомком
            else {
                if (curTree.right != null) {
                    curTree.right.parent = curTree.parent;
                }
                curTree.parent.left = curTree.right;
                curTree.right = tree.right;
                curTree.left = tree.left;
                tree.left.parent = curTree;
                tree.right.parent = curTree;
                curTree.parent = tree.parent;
                if (tree == tree.parent.left) {
                    tree.parent.left = curTree;
                } else if (tree == tree.parent.right) {
                    tree.parent.right = curTree;
                }
                return true;
            }
        }
        return false;
    }
}
