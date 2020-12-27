import hashlib 

str = "GeeksforGeeks"

result = hashlib.md5(str.encode())

print("The hexadecimal equivalent of hash is : ", end ="")

print(result.hexdigest())