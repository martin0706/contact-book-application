const path = require('path');

module.exports = {
  entry: './src/PersonTable.js', // Entry point of your application
  output: {
    path: path.resolve(__dirname, 'dist'), // Output directory
    filename: 'bundle.js' // Output bundle filename
  },
  module: {
    rules: [
      {
        test: /\.css$/, // Apply this loader to files ending in .css
        use: ['style-loader', 'css-loader'] // Use these loaders (applied from right to left)
      },
      // Add more loaders for other file types or additional configurations as needed
    ]
  }
};
