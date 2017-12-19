# -*- coding:utf-8 -*-

from socket import *

host = '127.0.0.1'
port = 8888


def main():

    # 1.创建socket
    client_socket = socket(AF_INET, SOCK_STREAM)

    # 2.指定服务器的地址和端口号
    server_addr = (host,port)
    client_socket.connect(server_addr)

    print('connect %s success' % str(server_addr))

    while True:
        # 3.给用户提示，让用户输入要检索的资料
        send_data = input('>>')       #输入要传输的数据
        # 退出
        if send_data == 'quit':
            break
        # 向服务器请求数据
        client_socket.send(send_data.encode())

    client_socket.close()

if __name__ == "__main__":
    main()
