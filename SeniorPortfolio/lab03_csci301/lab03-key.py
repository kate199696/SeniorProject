
#defines how to find the maximum of 3 numbers
def max(a, b, c):
    if (a > b):
        if (a > c):
            return a
        else:
            return c
    elif (b > c):
        return b
    else:
        return c
#prints the maximum
print max(5, 2, 1)

#finds the median in a list of numbers
def median(numbers):
    numbers.sort()
    if len(numbers) % 2 == 0:
        return (numbers[(len(numbers) / 2) - 1], numbers[len(numbers) / 2]) 
    else:
        return numbers[len(numbers) / 2]
#prints the median
print median([5, 2, 7, 3, 1, 8, 9, 14])

#returns true if a vowel is found
def vowel(text):
    vowels = "aeiou"
    for letter in vowels:
        if letter in text:
            return True
    return False

#prints the result
print vowel("FaR")

#returns true if all consonants
def consonant(text):
    consonants = "bcdfghjklmnpqrstvwxyz"
    count = 0
    for letter in consonants:
        if letter in text:
            count += 1
    
    if count == len(text):
        return True

    return False
#prints result
print consonant("rck")

#reverses the text
def reverse(text):
    return text[::-1]

#checks if the reverse equals the original
def palindrome(text):
    return text == reverse(text)

#prints the results
print palindrome("racecar")