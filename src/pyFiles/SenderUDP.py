import socket

host = "192.168.43.184"
port = 8889

address = (host, port)
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

while True:
    msg = input('请输入要处理的数据：')
    if not msg or msg == '88':
        break
    s.sendto(msg.encode(), address)

s.close()