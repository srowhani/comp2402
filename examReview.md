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

3. 
If you are currently growing the backing array, then the number of add and remove operations...


<strong>Lemma 2..1</strong>  If an empty ArrayStack is created and any sequence of 'm' calls to <code>add(i,x)</code> and <code>remove(i)</code> are performed, then the total time spent during all calls to  <code>resize</code>is <code>O(m)</code>

The amortized time for add and rmeove as a product of <code>i</code><sub>calls to resize</sub> is
<code>n<sub>i</sub>/2-1</code>

<h3>ArrayDeques</h3>
For FIFO, the ArrayStack is a poor implementation

Stimulates an infinite array

<code>int j</code> keeps track of the next element to remove

<code>int n</code> indicates the number of elements

The cursor lets us quickly perform resize operations. But the problem is indexing.

Using modular arithemetic, we're able to make pretend that our array is actually infinite, by overwriting null elements after a resize.

This looks something like this:

<code>a[(j+i) % a.length]</code>

To ensure we don't surpass the length of the array.


1. Describe, in words, how to perform an add(i,x) operation (a) if i < n/2 and (b) if i >= n/2

<code>

	if (i < n/2) {// shift a[0],..,a[i-1] left one position
	
		j = (j == 0) ? a.length - 1 : j - 1; //(j-1) mod a.length
	
		for (int k = 0; k <= i-1; k++)
			a[j+k] = a[j+k+1];
		
	} else { // shift a[i],..,a[n-1] right one position

		for (int k = n; k > i; k--)
	
			a[j+k] = a[j+k-1];
		
	}
</code>

Case 1: i < n/2

This is code I took from ArrayDeque2. It simulates modular arithmetic. If our cursor is pointing at 0,
we're intended to loop to the back. Otherwise we decrement it.

Case 2: i >= n/2

Set each value of a to be its the element before it. Which essentially shifts all the elements right.

2.
Running time of add(i,x) and remove(i)

O(1+min(i,n-1)) for each operation

3. Why can't we use <code>System.arraycopy()</code>

Because the cursor! We can't perform the same sort of looping arithmitic with that function.

4. Explain why, using an example, if a.length is a power of 2 then <code>(x mod a.length) == (x & (a.length-1))</code>. Why is this relevant when discussing ArrayDeques

Lets work it out

When a.length is not a power of two:
x=5

a.length = 3

Mod:
5%3 = 2

And Operation:

	  00000101
	& 00000010 (we subtracted 1)
	----------
	  00000000 WRONG

Now when a.length is a power of two:

x=5

a.length = 2

Mod:
5%2 = 1

And Operation:

	  00000101
	& 00000001 (we subtracted 1)
	----------
	  00000001 ! correct

That's it. It's mathematically sound. Prove me wrong!

