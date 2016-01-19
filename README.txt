Approach:

- Read in the cipher text
- Separate the cipher text into single columns
- Sort cipher text single columns into correct order based on transposition key
- Store frequency analysis results as well as letter pairs (Map<String, Integer>)
- Use frequency analysis results to distribute alphabet
- If frequency analysis numbers differ only by one (i.e. 6 and 5, or 5 and 4, not 6 and 4), include the letters in both
- Construct phrase from alphabet created
- Permutate over all combinations of letters available to each letter pair


Ideas:

— For frequency analysis numbers that differs only by one, distribute the alphabet by numbers. That is, when we come upon a frequent letter pair of 6, and there is another that is 5, assign 0 and 1 to both of these. Then when we permutate over the list (Possibly list of lists?) we can see that we will be using 0 as well as 1 index of the frequent strings and then we can run the length of each frequent string.