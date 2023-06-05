/** @type {import('next').NextConfig} */
const nextConfig = {}

module.exports = {
    async headers() {
      return [
        {
          source: '/api/:path*', // Replace with your API endpoint path
          headers: [
            { key: 'Access-Control-Allow-Origin', value: '*' },
            { key: 'Access-Control-Allow-Methods', value: 'GET, POST, PUT, DELETE, OPTIONS' },
            { key: 'Access-Control-Allow-Headers', value: 'X-Requested-With, Accept, Content-Type, Authorization' },
          ],
        },
      ];
    },
  };
    
