def maxB(a,b,c):#gets 3 numbers and finds the maximum
    maxVal = a
    if b>maxVal:
        maxVal = b
    if c>maxVal:
        maxVal = c
    return maxVal

def median(numbers):#finds the median in a list of numbers, even # of #s returns tuple
    count = len(numbers)
    numbers.sort()
    
    if count % 2 == 0:
        #find tuple solution
        numb1 = numbers[(count/2)-1]
        numb2 = numbers[(count/2)]
        var = numb1, numb2
        return var
    else:
        return numbers[(count/2)-1]

def vowel(text):#returns true if there is a vowel in a string, not including y
    count = 0
    for char in text:
        if char in "aAeEiIoOuU":
            count = count+1
    if(count > 0):
        return True
    else:
        return False
def consonant(text):#returns true if every character is a consonant;
    if vowel(text) == True:
        return False
    else:
        return True
def palindrome(text):#returns if inputed word is a palindrome,
    #make sure input is lowercase, make reverse word, compare two words
    text = text.lower()
    copy = reverse(text)
    if copy == text:
        return True
    else:
        return False

def reverse(text):
    #added reverse function that reverses the string input
    return text[::-1]


#test code
print maxB(1,4,20)
testNum = [1,3,45,2,6,12]
print median(testNum)
vow = "Lea"
string = "trey"
palin = "Racecr"

print vowel(vow)
print consonant(string)
print reverse(palin)
print palindrome(palin)