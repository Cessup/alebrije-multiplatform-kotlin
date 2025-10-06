// The copy-webpack-plugin dependency is needed in wasmJsMain for this to work

const CopyWebpackPlugin = require('copy-webpack-plugin');

config.plugins.push(
  new CopyWebpackPlugin({
    patterns: [
      // This copies the sql-wasm.wasm file from the node_modules
      { from: '../../node_modules/sql.js/dist/sql-wasm.wasm', to: '' }
    ]
  })
);

// This is required to resolve some Node.js specific features that don't exist in the browser
config.resolve = {
  fallback: {
    fs: false,
    path: false,
    crypto: false,
  }
};