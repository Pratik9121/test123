#include<bits/stdc++.h>
#include <queue>
using namespace std;

class HeapNode{
    public:
        int data;
        HeapNode* left;
        HeapNode* right;
        HeapNode* parent;

        HeapNode(int val) {
            data = val;
            left = NULL;
            right = NULL;
            parent = NULL;
        }
};


class Heap {
    HeapNode* root;
    public:
        Heap() {
            root = NULL;
        }

        HeapNode* lastparent() {
            queue<HeapNode*> q;
            q.push(root);
            HeapNode* temp;

            while(!q.empty()) {
                temp = q.front();
                q.pop();

                if (temp->left && temp->right) {
                    q.push(temp->left);
                    q.push(temp->right);
                } else {
                    break;
                }
            }

            return temp;
        }

        void upHeapify(HeapNode *temp) {
            if (temp->parent == NULL) {
                return;
            }
            if(temp->parent->data < temp->data) {
                swap(temp->parent->data, temp->data);
                upHeapify(temp->parent);
            }
        }

        void add(int data) {
            HeapNode* newnode = new HeapNode(data);
            cout << "Inserting : " << data<<"\n";

            if (root == NULL) {
                root = newnode;
                return;
            }

            HeapNode *lpar = lastparent();

            if(lpar->left == NULL) {
                lpar->left = newnode;
                newnode->parent = lpar;
            } else {
                lpar->right = newnode;
                newnode->parent = lpar;
            }

            upHeapify(newnode);
        }


        bool isEmpty() {
            if (root == NULL) {
                return true;
            } else {
                return false;
            }
        }

        HeapNode* lastNode() {
            queue<HeapNode*> q;
            q.push(root);
            HeapNode* last;

            while(!q.empty()) {
                last = q.front();
                q.pop();
                if (last->left) {
                    q.push(last->left);
                }
                if (last->right) {
                    q.push(last->right);
                }
            }
            return last;
        }


        void downHeapify(HeapNode* he) {
            HeapNode* largest = he;

            if (he->left && he->left->data > largest->data) {
                largest = he->left;
            }
            if (he->right && he->right->data > largest->data) {
                largest = he->right;
            }

            if (largest != he) {
                swap(largest->data, he->data);
                downHeapify(largest);
            }
        }

        void remove() {
            if (isEmpty()) {
                cout <<"Heap is Empty!!"<<endl;
                return;
            }
            cout << "Deleting : "<<root->data<<endl;

            HeapNode *last = lastNode();
            if (last == root)
            {
                delete last;
                root = NULL;
                return;

            }
            swap(root->data, last->data);
            HeapNode* pr = last->parent;

            if (pr->left == last) {
                delete last;
                pr->left = NULL;
            } else {
                delete last;
                pr->right = NULL;
            }

            downHeapify(root);

        }

        void inorder(HeapNode* he) {
            if (he != NULL) {
                inorder(he->left);
                cout<<he->data<<" ";
                inorder(he->right);
            }
        }

        void print() {
            cout << "Printing data of the Heap : ";
            HeapNode* temp = root;
            inorder(temp);
            cout<<endl;
        }
};


int main() {
    Heap h;
    while(1) {
        cout << "1. For insert" << endl;
        cout << "2. For Extract Max" << endl;
        cout << "3. For Display" << endl;
        cout << "4. Exit" << endl;
        int choice;
        cin >> choice;

        switch(choice) {
            case 1:
                int data;
                cout << "Enter Key : ";
                cin >> data;
                h.add(data);
                break;

            case 2:
                h.remove();

                break;

            case 3:
                h.print();
                break;

            case 4:
                exit(0);

        }
    }

}
