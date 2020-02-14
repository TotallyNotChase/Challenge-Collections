using System;
using System.Collections.Generic;

namespace Coin_Change_Permutation
{
    internal class EntryPoint
    {
        // Testing class for Coins.cs
        private static void Main(String[] args)
        {
            Dictionary<int, string> changeDict = new Dictionary<int, string>();
            changeDict.Add(10, "Dimes");
            changeDict.Add(5, "Nickels");
            changeDict.Add(1, "Pennies");
            Coins coins = new Coins(changeDict);
            coins.WaysOfRepresenting(17);
        }
    }
}
