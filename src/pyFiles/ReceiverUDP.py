
import socket  

host = "192.168.43.50"
port = 8889
address = (host, port)  
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  
s.bind(address)
  
while True:  
    data, addr = s.recvfrom(2048)
    if not data:  
        print("client has exist")
        break  
    print("received: "+data.decode()+" from"+str(addr))
    if "open the lamp" == data.decode():
        print("Success open lamp")

    if "open the windows" == data.decode():
        print ("Success open win")

    if "send the data" == data.decode():
        print ("Success send data")

    #此处写控制电机的代码
  
s.close()
