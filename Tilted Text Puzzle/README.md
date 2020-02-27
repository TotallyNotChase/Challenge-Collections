# The Problem
A list of strings is given,
```python
Puzzle = ["FUNCTIONRRIRAI",
          "RAIOONFRCCPWON",
          "PTCSNOBEUITOLO",
          "BNCACIANTOSLIH",
          "RBYOLILYNREFBT",
          "HYYNOGESTIBRIY",
          "AATTSIONCMCENP",
          "UORTENRRCBFVAU",
          "CEBEECVWIERORI",
          "PROCESSORTOPYF",
          "OHCOMPUTERHSOS",
          "YCYPRESREOSMRW",
          "OATHBRMVTHHCTR",
          "PGORWOOUIPSCHP"]
```

This list of strings is later tilted to create this list

```python
tilted = ['F',
          'RU',
          'PAN',
          'BTIC',
          'RNCOT',
          'HBCSOI',
          'AYYANNO',
          'UAYOCOFN',
          'COTNLIBRR',
          'PERTOIAECR',
          'ORBTSGLNUCI',
          'YHOEEIEYTIPR',
          'OCCCENOSNOTWA',
          'PAYOECRNTRSOOI',
          'GTPMSVRCIELLN',
          'OHRPSWCMBFIO',
          'RBEUOIBCRBH',
          'WRSTREFEIT',
          'OMRETRVNY',
          'OVEROOAP',
          'UTOHPRU',
          'IHSSYI',
          'PHMOF',
          'SCRS',
          'CTW',
          'HR',
          'P']
```

The task is to find out the original `Puzzle` list given the tilted list.

# My Solution
*I refer to the `tilted` list indexes as `line number`(s)*

* Find out the maximum index of the `Puzzle` list (referred to as `max_lines`), which is equal to the length of the `Puzzle` string, alternatively it is equal to the longest string in `tilted` list.

  In this case, this is `14`.

* Iterate through the `tilted`, and the strings within the list, whenever an item in the list is exhausted, line number is incremented

* The **very first character** of each item, should be in the *same index* as the line number (starting from 0).

  For example, in line 2 (actually 1, when starting from 0), the 1st character is `R`, this should be in the `1st` index (starting from 0) of our list

* The **second character**, will be in the *previous index*

  For example, in line 2 (actually 1, when starting from 0), the 2nd character is `U`, this should be in the `0th` index of our list

* For the next character, we'll once again have to *step down one index* and so on...

* Do this until line number is **greater than or equal** to the *length of `original` list* (alternatively, the length of the longest item in `puzzle` list), in this case `14`

* Once `line no >= max_lines`, `index` is no longer incremented and should stay at the max index, which is 13 in this case. Everything else will stay the same.

In the end, we should have the original list
