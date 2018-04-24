#include <stdlib.h>
#include <stdio.h>
#include <stddef.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#define MAXLINE 80

char *client_socket_path = "/tmp/echo2.socket";

int main(void)
{
   struct  sockaddr_un cliun, serun;
       int len;
       char buf[100];
       int sockfd, n;
       if ((sockfd = socket(AF_UNIX, SOCK_STREAM, 0)) < 0){
           perror("client socket error\n");
           exit(1);
       }

       // 一般显式调用bind函数，以便服务器区分不同客户端
       memset(&cliun, 0, sizeof(cliun));
       cliun.sun_family = AF_UNIX;
       strcpy(cliun.sun_path, client_socket_path);
       len = offsetof(struct sockaddr_un, sun_path) + strlen(cliun.sun_path);
       unlink(cliun.sun_path);
       if (bind(sockfd, (struct sockaddr *)&cliun, len) < 0) {
           perror("bind error\n");
           exit(1);
       }else{
           perror("Floating bar binding socket successfully");
       }

       printf("Enter your message:\n");
       while(fgets(buf, MAXLINE, stdin) != NULL) {
            n = write(sockfd, buf, strlen(buf));
            if ( n < 0 ) {
               printf("the other side has been closed.\n");
            }else {
                perror(buf);
//               write(STDOUT_FILENO, buf, n);
            }
       }
       close(sockfd);
       return 0;
}
