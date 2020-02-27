original = ["FUNCTIONRRIRAI",
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

puzzle = ['F',
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

max_lines = 14
solution = [''] * max_lines

decrement = 0
lineno = -1
for item in puzzle:
    lineno += 1
    index = lineno
    if lineno >= max_lines:
        index = max_lines - 1
    decrement = 0
    for char in item:
        solution[index - decrement] += char
        decrement += 1

assert(solution == original)
