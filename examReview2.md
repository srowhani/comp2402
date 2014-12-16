<h1>Exam Review Part</h1>

<h3>SkipLists</h3>
SkipList is a data structure that uses <code>Random.nextBoolean()</code> to determine whether
to create a new list level or just append to the current one.

This image from wikipedia well depicts the process of adding / finding elements

<img src='skiplist.gif'/>

<strong>Explain how the reverse search path is related to the following experiment: Toss a coin
repeatedly until the first time the coin comes up heads</strong>

It's related in the sense that the number of coin flips before the coin lands heads
determines the number of nodes that the reverse search path visits.

<i>
The number of nodes that the reverse search path visits at a particular level,  $ \mathtt{r}$, is related to the following experiment: Toss a coin. If the coin comes up as heads, then move up and stop. Otherwise, move left and repeat the experiment. The number of coin tosses before the heads represents the number of steps to the left that a reverse search path takes at a particular level.4.3 Lemma 4.2 tells us that the expected number of coin tosses before the first heads is 1.
</i>

<strong> If there are n elements in L0, what is the expected number of elements in L1? What
about in Li?</strong>

L<sub>i</sub> expects to have <code>i/2<sup>i</sup></code>

After each coin flip, the probability of increasing the level becomes smaller and smaller.

<strong>If there are n elements in L0, give an upper bound on the expected length of the search
path for any particular element.</strong>

Lemma 4..6   The expected length of a search path in a skiplist is **at most**:

  2logn + O(1)
  
<strong> Explain, briefly, how a skiplist can be used to implement the SortedSet interface.
What are the running times of operations <code>add(x), remove(x), contains(x)</code>?</strong>

Skiplists, when adding or searching for an element check to see, starting from the highest level,
if what it's searching for is greater or less than that element.

All these operations should take an expected time of <code>O(logn)</code>


