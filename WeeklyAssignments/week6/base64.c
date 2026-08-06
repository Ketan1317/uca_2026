#include <stdio.h>
#include <string.h>

// Base64 lookup table
const char BASE64[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

// Returns index of a Base64 character
int getIndex(char ch) {
    for (int i = 0; i < 64; i++) {
        if (BASE64[i] == ch)
            return i;
    }
    return -1;
}
// ENCODE
void encode() {

    char str[1000];

    printf("Enter Normal String : ");
    scanf("%[^\n]", str);

    int len = strlen(str);
    printf("\nEncoded String : ");

    // Process 3 bytes at a time
    for (int i = 0; i < len; i += 3) {

        // Read 3 bytes
        unsigned char char1 = str[i];
        unsigned char char2 = (i + 1 < len) ? str[i + 1] : 0;
        unsigned char char3 = (i + 2 < len) ? str[i + 2] : 0;

        // Combine into one 24-bit number
        unsigned int triple = (char1 << 16) | (char2 << 8) | char3;

        // Extract four 6-bit values
        char c1 = BASE64[(triple >> 18) & 63];
        char c2 = BASE64[(triple >> 12) & 63];
        char c3 = BASE64[(triple >> 6) & 63];
        char c4 = BASE64[triple & 63];

        // Handle padding
        if (i + 1 >= len) {
            c3 = '=';
            c4 = '=';
        }
        else if (i + 2 >= len) {
            c4 = '=';
        }

        printf("%c%c%c%c", c1, c2, c3, c4);
    }

    printf("\n");
}

// DECODE
void decode() {

    char str[1000];

    printf("Enter Base64 String : ");
    scanf("%s", str);

    int len = strlen(str);
    printf("\nDecoded String : ");

    // Process 4 Base64 characters at a time
    for (int i = 0; i < len; i += 4) {

        // Convert characters back to indices
        int s1 = getIndex(str[i]);
        int s2 = getIndex(str[i + 1]);
        int s3 = (str[i + 2] == '=') ? 0 : getIndex(str[i + 2]);
        int s4 = (str[i + 3] == '=') ? 0 : getIndex(str[i + 3]);

        // Combine four 6-bit groups
        // back into one 24-bit integer
        unsigned int triple = (s1 << 18) | (s2 << 12) | (s3 << 6) | s4;

        // Extract original bytes
        char char1 = (triple >> 16) & 255;
        char char2 = (triple >> 8) & 255;
        char char3 = triple & 255;

        // Print only real bytes
        printf("%c", char1);
        if (str[i + 2] != '=')
            printf("%c", char2);

        if (str[i + 3] != '=')
            printf("%c", char3);
    }
    printf("\n");
}

int main() {

    int ch;
    printf("BASE64 Encoding/Decoding\n");
    printf("1. Encode\n");
    printf("2. Decode\n");
    printf("Enter choice(0/1) : ");

    scanf("%d", &ch);
    getchar(); 

    switch (ch) {
        case 1:
            encode();
            break;

        case 2:
            decode();
            break;
        default:
            printf("Invalid Choice\n");
    }
    return 0;
}