<div>
<h1>Exam Review</h1>
Questions can be found here:<br/>
http://cglab.ca/~morin/teaching/2402/notes/questions.pdf<br/>
http://cglab.ca/~morin/teaching/2402/notes/questions2.pdf

<h2>Runtimes</h2>
<br/>
<img src='runtimes.png'/>

<h2>Java Collections Framework</h2>
<h3>Interfaces</h3>
1.
A Set is an interface for a data structure that only allows for unique elements, in an unordered collection of said type of elements.
A List is an ordered collection capable of storing duplicate elements.

2. 
The collection interface stores a collection of values, each element stored at a specific index of the data structure. Map’s store their values at a given key. Each entry has a key associated with a value that is used to lookup it’s value.

3. 
Storing students merely for the purpose of checking if the data structure contains a Student would be best suited for the Set Interface.

4. 
Having an ordered, quick to find structure would be the SortedSet interface

5. 
Information associated with each student? Use a SortedMap

6.
Two ways to accomplish a bag would be through:
<br/>
<code>new Bag \<Integer, T>()</code><br/>
Integer states how many time the element is repeated.

<strong>or</strong><br/>
	
<code>new Bag \<T, new ArrayList<T>>() </code>


ArrayList<T> would contain the amount of duplicate elements.

7. 
Iterator is only capable of searching forward and removing elements.

The list iterator can also do add, set, and iterate backwards.

<h3>Implementations</h3>

1.
Modifying the <code>equals</code> method can have serious implications on how the Set stores data.
The set interface is actually uses the Map Interface. That's how it's able to do lookup's so quickly.

You take the given value, you convert it to a key using <code>hashCode</code> function and the set uses this hashcode
to check if it contains said element.

2. 
Benefits of a <code>LinkedHashSet</code> over a <code>HashSet</code>?

The LHS has all the benefits of a HashSet, being able to do quick lookups, and store unique values. 
But the LinkedList in it also allows it to maintain order of entry.

3. 
Running time of <code>get(i) && set(i,x)</code> for an ArrayList vs. a LinkedList?

get:
ArrayList: O(1). Just return the value at the array's index.
LinkedList: 

For a doubly linked list, you'd be able to iterate backwords.
Therefore, <code>O(min(i, n-i) + 1)</code>

As for a SL List, <code>O(1+i)</code> is your only option

set:
see get ^

4. 
<code>add(i,x)</code>

An arraylist, in worst case would have to resize to make room, them shift all elements ahead of it to make room,
and then it would be able to place the element.
This works out to <code>O(1 + n - i)<sup>A</sup></code>

<sup>A</sup> denotes Amortized. Meaning this ignores the cost to resize.

DLL: <code>O(1 + min(i, n-i))</code>. 

SLL: <code>O(1 + i)</code>

5. 

Time complexity in a linked list is essentially just setting the cursor's position.

Once you've navigated, say to element n/2, there's no problem to add elements there.

For an ArrayList, adding to the middle will require O(1 + n - n/2) operations per <code>add</code> operation.

<h2>Lists as Arrays</h2>
Questions about the List Interface

<h3>ArrayStacks</h3>
1.
<a href='#runtimes'>Runtime Complexity of <code>get</code> and <code>set</code></a>
ArrayStacks are essentially just Arrays with bells and whistles. 
2.
ArrayStacks resize when:

<code>n+1 > a.length</code><br/>
The reason that we resize the ArrayStack to be double it's previous size, as opposed to just 1 or a constant
is to find a good balance between the time complexity required to resize the structure, and the space complexity
of storing the elements without excessively wasting memory.

3. If you are currently growing the backing array, then the number of add and remove operations...


<strong>Lemma 2..1</strong>  If an empty ArrayStack is created and any sequence of $ m\ge 1$ calls to <code>add(i,x)</code> and  $ <code>remove(i)</code> are performed, then the total time spent during all calls to  <code>resize</code>is <code>O(m)</code>
